package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.Intake_IntakingCommand;
import org.firstinspires.ftc.teamcode.commands.Outtake_IntakingCommand;
import org.firstinspires.ftc.teamcode.commands.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.ScoringLowBucketCommand;
import org.firstinspires.ftc.teamcode.commands.TransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmParallelCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.spec.GetSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.OutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.ScoringSpecCommand;
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

            /************** GAMEPAD 1 **************/

            // Intake Sample from field
            if (gamepad1.left_trigger > 0.2 && !robot.color.isFull()) {
                new IntakeInCommand().schedule();
            } else if (gamepad1.right_trigger > 0.2) {
                new IntakeSpitCommand().schedule();
            } else {
                robot.intake.stop();
            }

            //specimen
            if (gamepad1.left_bumper)
                new GetSpecCommand().schedule();
            else if (gamepad1.right_bumper)
                new OutSpecCommand().schedule();

            //opens claw
            if(gamepad1.a)
                new OuttakeClawOpenCommand().schedule();

            /************** GAMEPAD 2 **************/

            // Grab piece & put into bucket outtake position
            if (gamepad2.a) //
                new TransferCommand().schedule();
            else if (gamepad2.b)
                new Intake_IntakingCommand().schedule();
            else if (gamepad2.x)
                new ScoringSpecCommand().schedule();

            //Intake Arm positions
            if(gamepad2.left_trigger > 0.2) //low bucket score
                new IntakeArmParallelCommand().schedule();
             else if (gamepad2.right_trigger > 0.2) //transfer position for outtake
                new IntakeArmIntakeCommand().schedule();

            //slides and claws initial positions
            if(gamepad2.dpad_down)
                new Outtake_IntakingCommand().schedule();

            // Low and High Buckets
            if(gamepad2.left_bumper){ //low bucket score
                new ScoringLowBucketCommand().schedule();
            } else if (gamepad2.right_bumper){ //transfer position for outtake
                new ScoringHighBucketCommand().schedule();
            }



            // updating stuff
            if (robot.color.isFull() && !robot.color.slotState.equals(IntakeColorSensor.SlotState.YELLOW))
                spit(robot.color, robot.intake, alliance);

            robot.intakeArm.update();

            telemetry.addData("SLOT ", robot.color.slotState); // not updating for some reason
            telemetry.addData("IntakeArm Position", robot.intakeArm.telemetry(telemetry));
            telemetry.update();
            vs.update();
            CommandScheduler.getInstance().run();
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
//        robot.clawArm.intake();
        robot.clawWrist.transfer();
//        robot.outtakeClaw.close();
        // slides down
    }


}
