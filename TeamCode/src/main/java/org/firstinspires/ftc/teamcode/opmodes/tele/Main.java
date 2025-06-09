package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesRetractCommand;
//import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.NicolasCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.low.ScoringLowBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.commands.intake.RetractAutoCommand;
import org.firstinspires.ftc.teamcode.commands.spec.HighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.LowSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.util.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.util.Globals;

@TeleOp(name="Main", group ="TeleOp")
public class Main extends GreenLinearOpMode {
    double y, x, rx;
    double hsPow;
    double hangPow;
    boolean hanging;
    boolean upSlightly;

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
        addVertSlides();
        addHang();
        addIntakeColorSensor();
    }

    @Override
    public void periodic() {
        driveControl();
        drive();
        intakeColorSensor.startReading();

        if (intake.state == Intake.STATE.IN) {
            new WristDownCommand().schedule();
        } else {
            wrist.parallel();
        }

        /// GP1

        // Intake Field Sample
        if (gamepad1.left_trigger > 0.2) {
            new IntakeInCommand().schedule();
        } else if (gamepad1.right_trigger > 0.2) {
            new IntakeSpitCommand().schedule();
//        } else if (gamepad1.dpad_down){
//            new NicolasCommand().schedule();
        }
        else {
            intake.stop();
        }

        if (stickyG1.left_bumper) {
            if (vs.highspec){
                new LowSpecCommand().schedule();
            } else {
                new HighSpecCommand().schedule();
            }
        }

        // Opens Claw
        if (stickyG1.a) {
            outtakeClaw.toggle();
        } else if (stickyG1.b){
            new RetractAutoCommand().schedule();
//                new SlidesLiftSlightlyCommand().schedule(); //todo: figure out if i need this 2/26
        }

        /// GP2

        // All subsystems Intake + Transfer
        if (stickyG2.a) {
            outtakeClaw.toggle();
        } else if (stickyG2.dpad_up) {
            new SpecIntakeCommand().schedule();
        }
        if (stickyG2.dpad_down){
            new HorizontalSlidesRetractCommand().schedule();
        }
        hsPow = -gamepad2.left_stick_y;
        if (Math.abs(hsPow) > .1)
            horizontalSlides.manualSlide(hsPow);

//        hangPow = -gamepad2.right_stick_y;
//        if (Math.abs(hangPow) > .1) {
//            PTO.setHangPower(hangPow);
//        } else if (!hanging) PTO.setHangPower(0);

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
        } else if (stickyG1.b) {
            new VertSlidesStartCommand().schedule();
        }

        colorSensorPeriodic();
    }

    public void colorSensorPeriodic(){
        if (robot.color.isFull() && !robot.color.slotState.equals(IntakeColorSensor.SlotState.YELLOW)) {
            spit(alliance);
        } else if (robot.color.isFull() && robot.color.slotState.equals(IntakeColorSensor.SlotState.YELLOW) && intake.state == Intake.STATE.IN) {
            new RetractAutoCommand().schedule();
        }
    }

    public void drive() {
        if(Globals.fieldCentric) {
            if (stickyG1.left_stick_button) {
                gamepad1.rumble(200);
                drivetrain.setExternalHeading(Math.toRadians(90));
            }
            drivetrain.fieldCentricDrive(x, y, rx);
        } else {
            drivetrain.drive(x, y, rx);
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
                } else if (intake.state == Intake.STATE.IN) {
                    new RetractAutoCommand().schedule();
                }
                break;
            case BLUE:
                if (robot.color.slotState.equals(IntakeColorSensor.SlotState.RED)) {
                    robot.intake.spit();
                } else if (intake.state == Intake.STATE.IN){
                    new RetractAutoCommand().schedule();
                }
                break;
        }
    }
}

//        } else if (stickyG2.dpad_down) {
//            if (horizontalSlides.loc == HorizontalSlides.LOC.RETRACTED) {
//                new HorizontalSlidesExtendCommand().schedule();
//            } else {
//                new RetractAutoCommand().schedule();
////                new SlidesLiftSlightlyCommand().schedule();
//            }
