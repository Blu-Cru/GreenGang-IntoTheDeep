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
    static Alliance alliance;
    Drive drive;
    double y, x, rx;

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
        addIntakeArm();
        addVertSlides();

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

            // GAMEPAD 1

            // Intake Sample from field
            if (gamepad1.left_bumper && !robot.color.isFull()) {
                robot.intake.in();
            } else if (gamepad1.right_bumper) {
                robot.intake.spit();
            } else {
                robot.intake.stop();
            }

            // Grab piece & put into bucket outtake position
            if(gamepad1.x) {
                robot.outtakeClaw.open();
            }else if(gamepad1.b) {
                robot.outtakeClaw.close();
                robot.clawArm.bucket();
                robot.clawWrist.transfer();
            }

            // GAMEPAD 2

            // Perform arm / wrist transfer + intake movement
            if(gamepad2.x)
                robot.intakeArm.parallel();
            else if(gamepad2.y) {
                robot.intakeArm.intake();
                robot.intakeWrist.intake();}
            else if(gamepad2.b){
                robot.intakeArm.transfer();
                robot.intakeWrist.transfer();
                robot.outtakeClaw.open();
                robot.clawArm.intake();
                robot.clawWrist.intake();
            }

            // Controlling slides
            if(gamepad2.right_bumper){
                robot.vs.lowBucket();
            } else if(gamepad2.right_trigger>.2){
                robot.vs.high();
            } else if (gamepad2.left_bumper){
                robot.vs.lower();
                robot.clawArm.intake();
                robot.clawWrist.intake();
            } else if (gamepad2.left_trigger>.2){
                float pow = gamepad2.left_trigger;
                if(gamepad2.left_trigger> 0.75) {
                   robot.vs.setVSrotatePow(.75);
                }
                else {
                    robot.vs.setVSrotatePow(pow);
                }
            }

            // updating stuff
            if (robot.color.isFull() && !robot.color.slotState.equals(IntakeColorSensor.SlotState.YELLOW))
                spit(robot.color, robot.intake, alliance);

            robot.intakeArm.update();

            telemetry.addData("SLOT ", robot.color.slotState); // not updating for some reason
            telemetry.addData("IntakeArm Position", robot.intakeArm.telemetry(telemetry));
            telemetry.update();
            vs.update();
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

    public void bucket (Robot robot, int h) {
        robot.intakeArm.transfer();
        robot.intakeWrist.transfer();
        robot.outtakeClaw.open();
        robot.intake.spit();
        robot.outtakeClaw.close();
        // slides lift (based on h)
        robot.clawArm.bucket();
        robot.clawWrist.bucket();
        robot.outtakeClaw.open();
        // wait 2 sec
        robot.outtakeClaw.close();
        robot.clawWrist.intake();
        robot.clawArm.intake();
        // slides drop
    }
    public void autoSpec(Robot robot){
        // arm to spec, wrist to spec & claw to spec & slides to spec & open claw > slides down, arm reset, wrist reset, claw reset
        robot.clawArm.outSpec();
        robot.clawWrist.outSpec();
        // slides to spec
        robot.outtakeClaw.open();
        // drop off piece wait 2 sec
        robot.clawArm.intake();
        robot.clawWrist.transfer();
        robot.outtakeClaw.close();
        // slides down
    }


}
