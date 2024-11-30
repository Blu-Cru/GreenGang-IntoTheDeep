package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

/* CONTROLS:

gamepad1:

left stick:             Driving
right stick:            Rotation
left bumper (hold):     slower driving
a (x):                  close grabber
b (circle):             open grabber
x (square):             manual slide down
y (triangle):           manual slide up
dpad down:              base
dpad right:             low
dpad left:              med
dpad up:                high

gamepad2:

*/

@TeleOp(name="Main", group ="TeleOp")
public class Main extends GreenLinearOpMode {
    Alliance alliance;

    Drive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = Robot.getInstance().create(hardwareMap);

        addDrivetrain();
        addIntakeColorSensor();
        addIntake();
        addIntakeWrist();
        addStickyG1();
        addStickyG2();
        addClawArm();
        addOuttakeClaw();
        addClawWrist();
        addArm();
        // vs = new VertSlides(hardwareMap);

        alliance = Alliance.BLUE;
        drive = Drive.FIELDCENTRIC;
        robot.init();
        robot.color.startReading();

        while(opModeInInit()) {

            if(stickyG1.x) {
                alliance = alliance.flip();
            }
            if (stickyG1.b){
                drive = drive.flip();
            }

            stickyG1.update();
            stickyG2.update();
            telemetry.addData("ALLIANCE: ", alliance);
            telemetry.addData("DRIVE: ", drive);
            telemetry.update();
        }

        waitForStart();

        while(opModeIsActive()) {

            robot.color.read();
            driveControl();
            drive(drive);

            /* INTAKE */

            // intk
            if (gamepad1.left_bumper && !robot.color.isFull()) {
                robot.intake.in();
            } else if (gamepad1.right_bumper) {
                robot.intake.spit();
            } else {
                robot.intake.stop();
            }

            if (robot.color.isFull() && !robot.color.slotState.equals(IntakeColorSensor.SlotState.YELLOW))
                spit(robot.color, robot.intake, alliance);

            // intkake wrist
            if(gamepad1.a) //x button
                robot.intakeWrist.intake();
            else if(gamepad1.b) //circle button
                robot.intakeWrist.transfer();

            //Intake Arm Rotate
            if(gamepad1.dpad_down)
                robot.intakeArm.autoArmRotate(.2, arm.DOWN_POS);
            else if(gamepad1.dpad_right)
                robot.intakeArm.autoArmRotate(.2, arm.VERTICAL_POS);
            else if(gamepad1.dpad_up)
                robot.intakeArm.autoArmRotate(.2, arm.INIT);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            /* OUTTAKE Gamepad 2 */

            if(gamepad2.left_bumper)
                robot.outtakeClaw.close();
            if(gamepad2.right_bumper) //square
                robot.outtakeClaw.open();


            if(gamepad2.a) {
                robot.clawArm.intake();
                robot.clawWrist.intake(); }

            if(gamepad2.b) {
                robot.clawArm.bucket();
                robot.clawWrist.transfer(); }

            telemetry.addData("SLOT ", robot.color.slotState); // not updating for some reason
            telemetry.addData("IntakeArm Position", robot.intakeArm.telemetry(telemetry));
            telemetry.update();
        }
    }

}
