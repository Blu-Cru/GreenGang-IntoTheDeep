package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.DropDepositCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.low.ScoringLowBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.commands.spec.HighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.LowSpecCommand;
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
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeColorSensor;


@TeleOp(name="Main", group ="TeleOp")
public class Main extends GreenLinearOpMode {
    double y, x, rx;
    double hsPow;
    double hangPow;


    @Override
    public void initialize() {
        addDrivetrain();
        addIntake();
        addStickyG1();
        addStickyG2();
        addClawArm();
        addOuttakeClaw();
        addHorizontalSlides();
        addClawWrist();
        addVertSlides();
        addHang();
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
            intake.stop();
        }
        //else {
//            new IntakeStopCommand().schedule();
//        }

        if (stickyG1.left_bumper) {
            new HighSpecCommand().schedule();
        } else if (stickyG1.right_bumper) {
            new LowSpecCommand().schedule();
        }

        // Opens Claw
        if(stickyG1.a) {
            new OuttakeClawOpenCommand().schedule();
        }

        /************** GAMEPAD 2 **************/

        // All subsystems Intake + Transfer
        if (stickyG2.a) {
            new HorizontalSlidesExtendCommand().schedule();
        } else if (stickyG2.b) {
            new TransferCommand().schedule();
        } else if (stickyG2.dpad_down) {
            new OuttakeClawCloseCommand().schedule();
        } else if (stickyG2.x) {
            new HorizontalSlidesRetractCommand().schedule();
        } else if (stickyG2.dpad_up) {
            new OuttakeClawOpenCommand().schedule();
        } else if (stickyG2.dpad_left) {
            new TelePart1Command().schedule();
        }

        hsPow = -gamepad2.left_stick_y;
        if (Math.abs(hsPow) > .1)
            horizontalSlides.manualSlide(hsPow);

        hangPow = -gamepad2.right_stick_y;
        if(Math.abs(hangPow) > .1){
            hang.setHangPower(hangPow);
        } else hang.setHangPower(0);

        // Low and High Buckets
        if(stickyG2.left_bumper){
            new ScoringLowBucketCommand().schedule();
        } else if (stickyG2.right_bumper){
            new ScoringHighBucketCommand().schedule();
        }

        // Resets bot + Deposits sample into bucket
        if (gamepad2.left_trigger > 0.2){
            new ResetCommand().schedule();
        } else if (gamepad2.right_trigger > .2){
            new SlidesLiftSlightlyCommand().schedule();
        }

        // updating stuff
//        if (robot.color.isFull() && !robot.color.slotState.equals(IntakeColorSensor.SlotState.YELLOW)) {
//            spit(robot.color, robot.intake, alliance);
//        }
    }

    public void drive(Drive drive){
        switch(drive) {
            case FIELDCENTRIC:
                if (gamepad1.options) {
                    gamepad1.rumble(200);
                    drivetrain.setExternalHeading(Math.toRadians(90));
                }
                drivetrain.fieldCentricDrive(x, y, rx);
                break;
            case ROBOTCENTRIC:
                drivetrain.drive(x, y, rx);
                break;
        }
    }

    public void driveControl(){
        y = gamepad1.left_stick_x;
        x = -gamepad1.left_stick_y;
        rx = -gamepad1.right_stick_x;

        if(gamepad1.right_trigger > 0.4) {
            Drivetrain.drivePower = 0.3;
        }
        else {
            Drivetrain.drivePower = 0.6;
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

}
