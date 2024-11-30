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

            // intk wrist
            if(gamepad1.a) //x button
                robot.intakeWrist.intake();
            else if(gamepad1.b) //circle button
                robot.intakeWrist.transfer();

<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmodes/tele/Solo.java
            if(gamepad2.a) //x
                robot.arm.autoArmRotate(.2, arm.DOWN_POS);
            else if(gamepad2.y) //triangle
                robot.arm.autoArmRotate(.2, arm.VERTICAL_POS);
            else if(gamepad2.b) //circle
                robot.arm.autoArmRotate(.2, arm.TRANSFER_POS);
=======
            // intk arm
            if(gamepad2.a)
                robot.intakeArm.autoArmRotate(.5, arm.DOWN_POS);
            else if(gamepad2.y)
                robot.intakeArm.autoArmRotate(.5, arm.VERTICAL_POS);
            else if(gamepad2.b)
                robot.intakeArm.autoArmRotate(.5, arm.INIT);
>>>>>>> 9d22ba070270f49c051270c4c4542d9d55b9d531:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmodes/tele/Main.java


            /* OUTTAKE */

            if(gamepad1.y) //triangle
                robot.clawWrist.intake();
            if(gamepad1.x) //square
                robot.clawWrist.transfer();

            if(gamepad2.y) //triangle
<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmodes/tele/Solo.java
                robot.transfer.intake();
            if(gamepad2.x) //square
                robot.transfer.transfer();//arm

            robot.arm.update();
=======
                robot.clawArm.intake();
            if(gamepad2.x)
                robot.clawArm.bucket(); //arm
>>>>>>> 9d22ba070270f49c051270c4c4542d9d55b9d531:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmodes/tele/Main.java

            telemetry.addData("SLOT ", robot.color.slotState); // not updating for some reason
            telemetry.addData("IntakeArm Position", robot.intakeArm.telemetry(telemetry));
            telemetry.update();
        }
    }

}
