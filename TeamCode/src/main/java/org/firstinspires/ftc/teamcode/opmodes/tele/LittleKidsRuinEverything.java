package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.hs.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.common.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intake.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.common.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.common.subsystems.drive.DriveMode;
import org.firstinspires.ftc.teamcode.common.subsystems.intake.Intake;

@TeleOp(name="totally all of our controls in this one", group ="TeleOp")
public class LittleKidsRuinEverything extends GreenLinearOpMode {
    double y, x, rx;
    double hsPow;

    @Override
    public void initialize() {
        addDrivetrain();
        addIntake();
        addStickyG1();
        addClawArm();
        addOuttakeClaw();
        addHorizontalSlides();
        addIntakeWrist();
        addClawWrist();
        addIntakeColorSensor();
    }

    @Override
    public void periodic() {
        driveControl();
        drive(driveMode);
        intakeColorSensor.startReading();

        if (intake.state == Intake.STATE.IN || intake.state == Intake.STATE.NICOLAS) {
            new WristDownCommand().schedule();
        } else {
            wrist.parallel();
        }

        // Intake Field Sample
        if (gamepad1.left_trigger > 0.2) {
            new IntakeInCommand().schedule();
        } else if (gamepad1.right_trigger > 0.2) {
            new IntakeSpitCommand().schedule();
        } else {
            intake.stop();
        }

        // All subsystems Intake + Transfer
        if (stickyG1.dpad_left) {
            outtakeClaw.toggle();
        } else if (stickyG1.dpad_up) {
            new SpecIntakeCommand().schedule();
        }
        if (stickyG1.dpad_down) {
            new HorizontalSlidesRetractCommand().schedule();
        }
        hsPow = -gamepad1.left_stick_y;
        if (Math.abs(hsPow) > .1)
            horizontalSlides.manualSlide(hsPow);

        if (gamepad1.left_bumper) {
            new ResetCommand().schedule();
        } else if (gamepad1.right_bumper) {
            new TransferCommand().schedule();
        }

    }

    public void drive(DriveMode driveMode) {
        switch (driveMode) {
            case FIELDCENTRIC:
                if (stickyG1.left_stick_button) {
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

        drivetrain.drivePower = 0.3;
    }
}
