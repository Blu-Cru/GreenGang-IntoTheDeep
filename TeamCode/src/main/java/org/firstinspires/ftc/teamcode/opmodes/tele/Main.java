package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.DropDepositCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.low.ScoringLowBucketCommand;
import org.firstinspires.ftc.teamcode.commands.spec.TelePart1Command;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowSpecCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;


@TeleOp(name="Main", group ="TeleOp")
public class Main extends GreenLinearOpMode {
    static Alliance alliance;
    Drive drive;
    double y, x, rx;

    @Override
    public void initialize() {
        addDrivetrain();
        addIntake();
        addIntakeWrist();
        addStickyG1();
        addStickyG2();
        addClawArm();
        addOuttakeClaw();
        addClawWrist();
        addIntakeArm();
        addVertSlides();
    }

    @Override
    public void periodic()  {
        driveControl();
        drive(drive);

        /************** GAMEPAD 1 **************/

        // Intake Field Sample
        if (gamepad1.left_trigger > 0.2) {
            new IntakeInCommand().schedule();
        } else if (gamepad1.right_trigger > 0.2) {
            new IntakeSpitCommand().schedule();
        } else {
            new IntakeStopCommand().schedule();
        }

        if (gamepad1.left_bumper) {
            new TelePart1Command().schedule();
        } else if (gamepad1.right_bumper) {
            new VertSlidesHighSpecCommand().schedule();
        } else if (gamepad1.b) {
            new VertSlidesLowSpecCommand().schedule();
        }

        // Opens Claw
        if(gamepad1.a) {
            new OuttakeClawOpenCommand().schedule();
        }

        /************** GAMEPAD 2 **************/

        // All subsystems Intake + Transfer
        if (gamepad2.a) {
            new HorizontalSlidesExtendCommand().schedule();
        } else if (gamepad2.b) {
            new TransferCommand().schedule();
        } else if (gamepad2.y) {
            new OuttakeClawCloseCommand().schedule();
        } else if (gamepad2.x) {
            new HorizontalSlidesRetractCommand().schedule();
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
//        if (robot.color.isFull() && !robot.color.slotState.equals(IntakeColorSensor.SlotState.YELLOW)) {
//            spit(robot.color, robot.intake, alliance);
//        }
    }

//    public void spit(IntakeColorSensor color, Intake intake, Alliance alliance){
//        switch(alliance) {
//            case RED:
//                if (robot.color.slotState.equals(IntakeColorSensor.SlotState.BLUE)) {
//                    robot.intake.spit();
//                }
//                break;
//            case BLUE:
//                if (robot.color.slotState.equals(IntakeColorSensor.SlotState.RED)) {
//                    robot.intake.spit();
//                }
//                break;
//        }
//    }

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
