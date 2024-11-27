package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.arm.Arm;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.gamepad.StickyGamepad;
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeWrist;

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
*/

@TeleOp(name="Solo", group ="TeleOp")
public class Solo extends GreenLinearOpMode {
    Alliance alliance;
    double y, x, rx;
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

        // TODO: these need to be implemented to be used in this opmode
        addClawArm();
        addOuttakeClaw();
        addClawWrist();
        addArm();

        // vs = new VertSlides(hardwareMap);

        alliance = Alliance.BLUE;
        drive = Drive.FIELDCENTRIC;

        while(opModeInInit()) {
            robot.init();
            robot.color.startReading();

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

            /* INTAKE */

            robot.color.read();
            driveControl();
            drive(drive);

            if (gamepad1.left_bumper && !robot.color.isFull()) {
                robot.intake.in();
            } else if (gamepad1.right_bumper) {
                robot.intake.spit();
            } else {
                robot.intake.stop();
            }

            if (robot.color.isFull())
                spit(robot.color, robot.intake, alliance);

            if(gamepad1.a) //x button
                robot.intakeWrist.intake();
            else if(gamepad1.b) //circle button
                robot.intakeWrist.transfer();

            // bot.telemetry(telemetry);
            telemetry.addData("SLOT ", robot.color.slotState); // not updating for some reason
            telemetry.update();
        }
    }

    public void spit(IntakeColorSensor color, Intake intake, Alliance alliance){
        switch(alliance) {
            case RED:
                if (robot.color.slotState.equals(IntakeColorSensor.SlotState.BLUE)) {
                    robot.intake.spit();
                }
                break;
            case BLUE:
                if (robot.color.slotState.equals(IntakeColorSensor.SlotState.RED)) {
                    robot.intake.spit();
                }
                break;
        }
    }

    public void drive(Drive drive){
        switch(drive) {
            case FIELDCENTRIC:
                if (gamepad1.options) {
                    robot.drivetrain.setExternalHeading(Math.toRadians(90));
                }
                robot.drivetrain.fieldCentricDrive(x, y, rx);
                break;
            case ROBOTCENTRIC:
                robot.drivetrain.drive(x, y, rx);
                break;
        }
    }

    public void driveControl(){
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = -gamepad1.right_stick_x;

        if(gamepad1.right_trigger > 0.4) {
            Drivetrain.drivePower = 0.3;
        }
        else {
            Drivetrain.drivePower = 0.6;
        }
    }
}
