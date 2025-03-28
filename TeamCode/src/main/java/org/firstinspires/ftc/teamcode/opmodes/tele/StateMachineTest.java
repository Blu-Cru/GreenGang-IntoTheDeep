package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.low.ScoringLowBucketCommand;
import org.firstinspires.ftc.teamcode.commands.spec.HighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.LowSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;

@TeleOp(name="StateMachineTest", group ="TeleOp")

public class StateMachineTest extends GreenLinearOpMode {
    double y, x, rx;
    double hsPow;
    double hangPow;
    boolean hanging;
    enum State{
        RETRACTED,
        LOW_BUCKET,
        HIGH_BUCKET,
        ABOVE_SPEC,
        SPEC_DUNK,
        HORIZ_EXTENDED,
        HANG_EXTENDED,
        HANG_RETRACTED,
        SPEC_INTAKE,
        CLAW_OPEN,
        SLIDES_LIFTED_SLIGHTLY,
        CLAW_CLOSED,
        INTAKING,
        SPITTING
    }
StateMachine sm;
    public void initialize(){
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
        sm = new StateMachineBuilder()
                //RETRACTED
                .state(State.RETRACTED)
                .onEnter(() -> {
                    new ResetCommand().schedule();

                })

                .transition(() -> stickyG2.a, State.CLAW_OPEN, () ->{
                    new OuttakeClawOpenCommand().schedule();
                })
                .transition(() -> stickyG2.a, State.CLAW_CLOSED, () ->{
                    new OuttakeClawCloseCommand().schedule();
                })

                .transition(()-> Math.abs(-gamepad2.right_stick_y) > 0.1, State.HORIZ_EXTENDED)
                .transition(()-> gamepad1.left_bumper, State.ABOVE_SPEC,()->{
                    new HighSpecCommand().schedule();
                })
                .transition(()-> gamepad2.left_bumper, State.HIGH_BUCKET,()->{
                    new ScoringHighBucketCommand().schedule();
                })
                .transition(()-> gamepad1.right_bumper, State.LOW_BUCKET,()->{
                    new ScoringLowBucketCommand().schedule();
                })
                .transition(()-> gamepad2.right_bumper, State.SLIDES_LIFTED_SLIGHTLY,()->{
                    new SlidesLiftSlightlyCommand().schedule();
                })

                .transition(()-> gamepad1.left_trigger > 0.2, State.INTAKING, ()->{
                    new IntakeInCommand().schedule();
                })
                .transition(()-> gamepad1.right_trigger > 0.2, State.SPITTING,()-> {
                    new IntakeSpitCommand().schedule();
                })
                //LOW BUCKET
                .state(State.LOW_BUCKET)
                .onEnter(() ->{
                    new ScoringLowBucketCommand().schedule();
                })
                .transition(()-> gamepad2.left_bumper, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
                .transition(()-> gamepad2.right_trigger > 0.2, State.HIGH_BUCKET,()->{
                    new ScoringHighBucketCommand().schedule();
                })
                //HIGH BUCKET
                .state(State.HIGH_BUCKET)
                .onEnter(() ->{
                    new ScoringHighBucketCommand().schedule();
                })
                .transition(()-> gamepad2.left_bumper, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
                .transition(()-> gamepad2.left_trigger > 0.2, State.LOW_BUCKET,()->{
                    new ScoringLowBucketCommand().schedule();
                })
                //HIGH SPECIMEN
                .state(State.ABOVE_SPEC)
                .transition(() -> gamepad1.left_bumper, State.SPEC_DUNK,() ->{
                    new LowSpecCommand().schedule();
                })
                .transition(()-> gamepad2.left_bumper, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
                //SPECIMEN DUNK
                .state(State.SPEC_DUNK)
                .onEnter(()->{
                    new LowSpecCommand().schedule();
                })
                .transition(()-> gamepad1.left_bumper, State.ABOVE_SPEC,()->{
                    new HighSpecCommand().schedule();
                })
                .transition(()-> gamepad2.left_bumper, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
                //SPECIMEN INTAKE
                .state(State.SPEC_INTAKE)
                .onEnter(()->{
                    new SpecIntakeCommand().schedule();
                })
                .transition(()-> gamepad1.right_bumper, State.SPEC_DUNK,()->{
                    new HighSpecCommand().schedule();
                })
                .transition(()-> gamepad2.left_bumper, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
                //INTAKE EXTENDED
                .state(State.HORIZ_EXTENDED)
                .onEnter(()->{
                    new HorizontalSlidesExtendCommand().schedule();
                })

                .transition(()-> gamepad2.dpad_down, State.RETRACTED,()->{
                    new HorizontalSlidesRetractCommand().schedule();
                })
                .transition(()-> gamepad2.left_bumper, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
//            .state(State.HANG_EXTENDED)
//            .onEnter(()->{
//                new Hang
//            }
                //SLIDES LIFTED SLIGHTLY
                .state(State.SLIDES_LIFTED_SLIGHTLY)
                .onEnter(()->{
                    new SlidesLiftSlightlyCommand().schedule();
                })
                .transition(()-> gamepad2.left_bumper, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
//            .transition(()-> gamepad2.right_stick_y, State.HORIZ_EXTENDED{
//
//
//                if (Math.abs(hsPow) > .1)
//                    horizontalSlides.manualSlide(hsPow);
//            })




                .build();
        sm.setState(State.RETRACTED);
        sm.start();

    }
    @Override
    public void periodic(){
        driveControl();
        drive(drive);
        y = gamepad1.left_stick_y;
        x = -gamepad1.left_stick_x;
        rx = -gamepad1.right_stick_x;

        //Robot moves slower
        if(gamepad1.right_trigger > 0.4) {
            drivetrain.drivePower = 0.3;
        }
        else {
            drivetrain.drivePower = 0.6;
        }

        drivetrain.drive(x, y, rx);

        sm.update();

    }
    public void drive(Drive drive) {
        switch (drive) {
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

        if (gamepad1.right_trigger > 0.4) {
            drivetrain.drivePower = 0.3;
        } else {
            drivetrain.drivePower = 0.6;
        }
    }
}
