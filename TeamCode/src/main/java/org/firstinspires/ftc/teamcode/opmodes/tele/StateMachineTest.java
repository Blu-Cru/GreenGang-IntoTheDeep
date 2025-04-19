package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.hs.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawToggleCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.common.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.common.commands.bucket.low.ScoringLowBucketCommand;
import org.firstinspires.ftc.teamcode.common.commands.intake.RetractAutoCommand;
import org.firstinspires.ftc.teamcode.common.commands.spec.HighSpecCommand;
import org.firstinspires.ftc.teamcode.common.commands.spec.LowSpecCommand;
import org.firstinspires.ftc.teamcode.common.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intake.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.common.subsystems.drive.DriveMode;

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

                // RETRACTED
                .state(State.RETRACTED)

//                .onEnter(() -> {
//                    new ResetCommand().schedule();
//
//                })

//                .transition(() -> stickyG2.a, State.CLAW_OPEN, () ->{
//                    new OuttakeClawOpenCommand().schedule();
//                })
//                .transition(() -> stickyG2.a, State.CLAW_CLOSED, () ->{
//                    new OuttakeClawCloseCommand().schedule();
//                })


                .transition(()-> Math.abs(-gamepad2.left_stick_y) > 0.1, State.HORIZ_EXTENDED)

                .transition(()-> stickyG2.dpad_up, State.SPEC_INTAKE,()->{
                    new SpecIntakeCommand().schedule();
                })
                .transition(()-> stickyG1.left_bumper, State.ABOVE_SPEC,()->{
                    new HighSpecCommand().schedule();
                })

                .transition(()-> gamepad2.right_bumper, State.HIGH_BUCKET,()->{
                    new ScoringHighBucketCommand().schedule();
                })

                .transition(()-> gamepad2.left_bumper, State.LOW_BUCKET,()->{
                    new ScoringLowBucketCommand().schedule();
                })

                .transition(()-> gamepad2.right_trigger>0.2, State.SLIDES_LIFTED_SLIGHTLY,()->{
                    new SlidesLiftSlightlyCommand().schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }

                })

//                .transition(()-> gamepad1.left_trigger > 0.2, State.INTAKING, ()->{
//                    new IntakeInCommand().schedule();
//                })
//                .transition(()-> gamepad1.right_trigger > 0.2, State.SPITTING,()-> {
//                    new IntakeSpitCommand().schedule();
//                })

                //LOW BUCKET
                .state(State.LOW_BUCKET)


                .transition(()-> gamepad2.left_trigger>0.2, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })

                .transition(()-> gamepad2.right_bumper, State.HIGH_BUCKET,()->{
                    new ScoringHighBucketCommand().schedule();
                })

                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }


                    hsPow = -gamepad2.left_stick_y;
                    if (Math.abs(hsPow) > .1){
                        horizontalSlides.manualSlide(hsPow);}
                })
                //HIGH BUCKET
                .state(State.HIGH_BUCKET)

                .transition(()-> gamepad2.left_trigger>0.2, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
                .transition(()-> gamepad2.left_bumper, State.LOW_BUCKET,()->{
                    new ScoringLowBucketCommand().schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }

                })
                //HIGH SPECIMEN
                .state(State.ABOVE_SPEC)


                .transition(() -> gamepad1.left_bumper, State.SPEC_DUNK,() ->{
                    new LowSpecCommand().schedule();
                })

                .transition(()-> gamepad2.left_trigger>-0.2, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })

                //SPECIMEN DUNK
                .state(State.SPEC_DUNK)


                .transition(()-> gamepad1.left_bumper, State.ABOVE_SPEC,()->{
                    new HighSpecCommand().schedule();
                })

                .transition(()-> gamepad2.left_trigger>0.2, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }
                })
                //SPECIMEN INTAKE

                .state(State.SPEC_INTAKE)

                .transition(()-> gamepad1.right_bumper, State.SPEC_DUNK,()->{
                    new HighSpecCommand().schedule();
                })
                .transition(()-> gamepad2.left_trigger >0.2, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })

                //INTAKE EXTENDED

                .state(State.HORIZ_EXTENDED)

                .loop(()->{
                    hsPow = -gamepad2.left_stick_y;
                    if (Math.abs(hsPow) > .1){
                        horizontalSlides.manualSlide(hsPow);}

                })
                .transition(()-> gamepad2.dpad_down, State.RETRACTED,()->{
                    new HorizontalSlidesRetractCommand().schedule();
                })
                .transition(()-> gamepad2.left_trigger > 0.2, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })

                .transition(() -> gamepad1.right_trigger > 0.2, State.SPITTING, ()->{
                    new IntakeSpitCommand().schedule();
                })
                .transition(() -> gamepad1.left_trigger > 0.2, State.INTAKING, ()->{
                    new IntakeInCommand().schedule();
                    new WristDownCommand().schedule();
                })
                .transition(()-> gamepad1.right_trigger <=0.2, State.HORIZ_EXTENDED,()->{
                    new IntakeStopCommand().schedule();
                })
                .transition(()-> gamepad1.left_trigger <=0.2, State.HORIZ_EXTENDED,()->{
                    new IntakeStopCommand().schedule();
                })
                //INTAKING

                .state(State.INTAKING)
                .transition(()-> gamepad1.left_trigger <=0.2, State.HORIZ_EXTENDED, ()->{
                    new IntakeStopCommand().schedule();
                    new WristParallelCommand().schedule();
                })

                //SPITTING
                .state(State.SPITTING)
                .transition(()-> gamepad1.right_trigger <=0.2, State.HORIZ_EXTENDED, ()->{
                    new IntakeStopCommand().schedule();
                    new WristParallelCommand().schedule();
                })





//                // HANGING
//                .state(State.HANG_EXTENDED)
//                .onEnter(()->{
//                    new VertSlidesHangAboveCommand().schedule();
//                })
//                .transition(() -> gamepad1.dpad_down, State.HANG_RETRACTED, ()->{
//                    new VertSlidesHangDunkCommand().schedule();
//                })
//
//                // RETRACTED HANG
//                .state(State.HANG_EXTENDED)
//                .onEnter(()->{
//                    new VertSlidesHangDunkCommand().schedule();
//                })
//                .transition(() -> gamepad1.dpad_up, State.HANG_RETRACTED, ()->{
//                    new VertSlidesHangAboveCommand().schedule();
//                })

                //SLIDES LIFTED SLIGHTLY

                .state(State.SLIDES_LIFTED_SLIGHTLY)
                .onEnter(()->{
                    new SlidesLiftSlightlyCommand().schedule();
                })
                .transition(()-> gamepad2.left_trigger >0.2, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
                .transition(()-> gamepad1.b, State.RETRACTED,()->{
                    new RetractAutoCommand().schedule();
                })



//            .transition(()-> gamepad2.right_stick_y, State.HORIZ_EXTENDED{
//                if (Math.abs(hsPow) > .1)
//                    horizontalSlides.manualSlide(hsPow);
//            })

                .build();
        sm.setState(State.RETRACTED);
        sm.start();
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("Robot State: ", sm.getState());
    }

    @Override
    public void periodic(){
        driveControl();
        drive(driveMode);
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

        sm.update();
        telemetry.update();
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

        if (gamepad1.right_trigger > 0.4) {
            drivetrain.drivePower = 0.3;
        } else {
            drivetrain.drivePower = 0.6;
        }
    }
}
