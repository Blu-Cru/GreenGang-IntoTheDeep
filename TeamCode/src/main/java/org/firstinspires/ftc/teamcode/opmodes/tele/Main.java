package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.DropDepositCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.outtake.OuttakeIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.low.ScoringLowBucketCommand;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmParallelCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecIntake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.commands.spec.OutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.ScoringSpecCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.Robot;


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

            /************** GAMEPAD 1 **************/

            // Intk Field Sample
            if (gamepad1.left_trigger > 0.2 && !robot.color.isFull()) {
                new IntakeInCommand().schedule();
            } else if (gamepad1.right_trigger > 0.2) {
                new IntakeSpitCommand().schedule();
            } else {
                robot.intake.stop();
            }

            // Specimen
            if (gamepad1.left_bumper) {
                new AutoSpecIntake().schedule();
            } else if (gamepad1.right_bumper) {
                new AutoSpecOuttake().schedule();
            }

            // Opens Claw
            if(gamepad1.a) {
                new OuttakeClawOpenCommand().schedule();
            }

            /************** GAMEPAD 2 **************/

            // All subsystems Intake + Transfer
            if (gamepad2.a) {
                new IntakeIntakeCommand().schedule();
            } else if (gamepad2.b) {
                new TransferCommand().schedule();
            } else if (gamepad2.y) {
                // Manual claw close
                new OuttakeClawCloseCommand().schedule();
            }

            // Low and High Buckets
            if(gamepad2.left_bumper){
                new ScoringLowBucketCommand().schedule();
            } else if (gamepad2.right_bumper){
                new ScoringHighBucketCommand().schedule();
            }

            // Resets bot + Deposits sample into bucket
            if (gamepad2.left_trigger > 0.2){
                new ResetCommand().schedule();
            } else if (gamepad2.right_trigger > .2){
                new DropDepositCommand().schedule();
            }

            // updating stuff
            if (robot.color.isFull() && !robot.color.slotState.equals(IntakeColorSensor.SlotState.YELLOW)) {
                spit(robot.color, robot.intake, alliance);
            }

            robot.intakeArm.update();
            vs.update();

            telemetry.addData("IntakeArm Position", robot.intakeArm.telemetry(telemetry));
            telemetry.update();
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

}
