package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.low.ScoringLowBucketCommand;
import org.firstinspires.ftc.teamcode.commands.intake.RetractAutoCommand;
import org.firstinspires.ftc.teamcode.commands.spec.HighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.LowSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.TelePart1Command;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.outtake.outtake.OuttakeClaw;
import org.firstinspires.ftc.teamcode.subsystems.slides.HorizontalSlides;


@TeleOp(name="Main", group ="TeleOp")
public class Main extends GreenLinearOpMode {
    double y, x, rx;
    double hsPow;
    double hangPow;
    boolean hanging;
    boolean closed;
    boolean extended;
    boolean highspec;
    boolean intakeIn;


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
        addIntakeColorSensor();
    }

    @Override
    public void periodic() {
        driveControl();
        drive(drive);

        /************** GAMEPAD 1 **************/

        // Intake Field Sample
        if (gamepad1.left_trigger > 0.2) {
            new IntakeInCommand().schedule();
            intakeIn = true;
        } else if (gamepad1.right_trigger > 0.2) {
            new IntakeSpitCommand().schedule();
            intakeIn = false;
        } else {
            intake.stop();
            intakeIn = false;
        }

        if (stickyG1.left_bumper) {
            if (highspec){
                new LowSpecCommand().schedule();
            } else {
                new HighSpecCommand().schedule();
            }
            highspec = !highspec;
        }

        // Opens Claw
        if (stickyG1.a) {
            if (closed) {
                new OuttakeClawCloseCommand().schedule();
            } else {
                new OuttakeClawOpenCommand().schedule();
            }
            closed = !closed;
        }

        /************** GAMEPAD 2 **************/

        // All subsystems Intake + Transfer
        if (stickyG2.a) {
            if (closed) {
                new OuttakeClawCloseCommand().schedule();
            } else {
                new OuttakeClawOpenCommand().schedule();
            }
            closed = !closed;
        } else if (stickyG2.dpad_down) {
            if (!extended) {
                new HorizontalSlidesExtendCommand().schedule();
            } else {
                new RetractAutoCommand().schedule();
            }
            extended = !extended;
        } else if (stickyG2.dpad_up) {
            new TelePart1Command().schedule();
        }

        hsPow = -gamepad2.left_stick_y;
        if (Math.abs(hsPow) > .1)
            horizontalSlides.manualSlide(hsPow);

        hangPow = -gamepad2.right_stick_y;
        if (Math.abs(hangPow) > .1) {
            hang.setHangPower(hangPow);
        } else if (!hanging) hang.setHangPower(0);

        if (stickyG2.dpad_right) {
            hanging = !hanging;
        }

        // Low and High Buckets
        if (stickyG2.left_bumper) {
            new ScoringLowBucketCommand().schedule();
        } else if (stickyG2.right_bumper) {
            new ScoringHighBucketCommand().schedule();
        }

        // Resets bot + Deposits sample into bucket
        if (gamepad2.left_trigger > 0.2) {
            new ResetCommand().schedule();
        } else if (gamepad2.right_trigger > .2) {
            new SlidesLiftSlightlyCommand().schedule();
        }

        // updating stuff
        intakeColorSensor.startReading();
        intakeColorSensor.update();
        if (robot.color.isFull() && !robot.color.slotState.equals(IntakeColorSensor.SlotState.YELLOW)) {
            spit(alliance);
        } else if (robot.color.isFull() && robot.color.slotState.equals(IntakeColorSensor.SlotState.YELLOW) && intakeIn) {
            new RetractAutoCommand().schedule();
        }
    }

    public void drive(Drive drive) {
        switch (drive) {
            case FIELDCENTRIC:
                if (stickyG1.b) {
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

    public void driveControl() {
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = -gamepad1.right_stick_x;

        if (gamepad1.right_trigger > 0.4) {
            drivetrain.drivePower = 0.3;
        } else {
            drivetrain.drivePower = 0.6;
        }
    }


    public void spit(Alliance alliance) {
        switch (alliance) {
            case RED:
                if (robot.color.slotState.equals(IntakeColorSensor.SlotState.BLUE)) {
                    robot.intake.spit();
                } else if (intakeIn) {
                    new RetractAutoCommand().schedule();
                }
                break;
            case BLUE:
                if (robot.color.slotState.equals(IntakeColorSensor.SlotState.RED)) {
                    robot.intake.spit();
                } else if (intakeIn){
                    new RetractAutoCommand().schedule();
                }
                break;
        }
    }

    public bucketHeading() {

    }
}

