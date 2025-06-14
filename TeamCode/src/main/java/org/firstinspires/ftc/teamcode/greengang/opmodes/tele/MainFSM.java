package org.firstinspires.ftc.teamcode.greengang.opmodes.tele;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.low.ScoringLowBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawLooseCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawLooseToggleCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawToggleCommand;
//import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.Level;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretFlipCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretTurn90Command;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.HighSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.LowSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;

@TeleOp(name="Main FSM", group ="TeleOp")

public class MainFSM extends GreenLinearOpMode {
    double y, x, rx;
    double hsPow, wristRotationPow;
    double hangPow;
    boolean hanging;
    enum State{
        RETRACTED,
        LOW_BUCKET,
        HIGH_BUCKET,
        HIGH_SPEC,

        HORIZ_EXTENDED,
        HANG_EXTENDED,
        HANGING,
        HANG_RETRACTED,
        SPEC_INTAKE,
        CLAW_OPEN,
        CLAW_CLOSED,
        INTAKING,
        SPITTING,
        LOW_SPEC
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
        addTurret();
        addClawDistanceSensor();
        sm = new StateMachineBuilder()

                // RETRACTED
                .state(State.RETRACTED)

                .transition(()-> Math.abs(-gamepad2.left_stick_y) > 0.1, State.HORIZ_EXTENDED)
                .transition(()-> gamepad1.left_bumper, State.SPEC_INTAKE,()->{
                    new SpecIntakeCommand().schedule();
                })
//                .transition(()-> stickyG1.left_bumper, State.HIGH_SPEC,()->{
//                    new LowSpecCommand().schedule();
//                })
//                .transition(()-> stickyG1.right_bumper, State.LOW_BUCKET,()->{
//                    new HighSpecCommand().schedule();
//                })
                .transition(()-> stickyG2.left_bumper, State.LOW_BUCKET,()->{
                    new ScoringLowBucketCommand().schedule();
                })
                .transition(()-> stickyG2.right_bumper, State.HIGH_BUCKET,()->{
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
//                .transition(()-> gamepad1.dpad_down, State.HANG_EXTENDED)
                .transition(()-> gamepad1.left_trigger > 0.2, State.INTAKING, ()->{
                    new IntakeInCommand().schedule();
                })
                .transition(()-> gamepad1.right_trigger > 0.2, State.SPITTING,()-> {
                    new IntakeSpitCommand().schedule();
                })

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
                    if(stickyG1.dpad_right){
                        new TurretTurn90Command().schedule();
                    }
                    if(stickyG1.dpad_left){
                        new TurretFlipCommand().schedule();
                    }
                    wristRotationPow = -gamepad1.left_stick_y;
                    if (Math.abs(wristRotationPow) > 0.1) {
                        robot.turret.manualRotate(wristRotationPow / 500);
                    }
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
                    if(stickyG1.dpad_right){
                        new TurretTurn90Command().schedule();
                    }
                    if(stickyG1.dpad_left){
                        new TurretFlipCommand().schedule();
                    }
                    wristRotationPow = -gamepad1.left_stick_y;
                    if (Math.abs(wristRotationPow) > 0.1) {
                        robot.turret.manualRotate(wristRotationPow / 500);
                    }
                })

                //HIGH SPECIMEN
                .state(State.HIGH_SPEC)
                .transition(()-> gamepad2.left_trigger>0.2, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
                .transition(()-> gamepad1.left_bumper, State.SPEC_INTAKE, ()->{
                    new SpecIntakeCommand().schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }
                    if(stickyG1.dpad_right){
                        new TurretTurn90Command().schedule();
                    }
                    if(stickyG1.dpad_left){
                        new TurretFlipCommand().schedule();
                    }

                })

                .state(State.LOW_SPEC)
                .transition(()-> gamepad2.left_trigger>0.2, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }
                    if(stickyG1.dpad_right){
                        new TurretTurn90Command().schedule();
                    }
                    if(stickyG1.dpad_left){
                        new TurretFlipCommand().schedule();
                    }
                })
                .transition(()-> gamepad1.left_bumper, State.SPEC_INTAKE, ()->{
                    new SpecIntakeCommand().schedule();
                })


                //SPECIMEN INTAKE
                .state(State.SPEC_INTAKE)
//                .transition(()-> distanceSensor.isFull(), State.HIGH_SPEC, ()->{
//                    new SequentialCommandGroup(
//                            new OuttakeClawLooseCloseCommand(),
//                            new WaitCommand(100),
//                            new HighSpecCommand()
//                    ).schedule();
//                })

                .transition(()-> stickyG1.left_bumper, State.LOW_SPEC, ()->{
                    new LowSpecCommand().schedule();
                })
                .transition(()-> gamepad1.right_bumper, State.HIGH_SPEC,()->{
                    new HighSpecCommand().schedule();
                })
                .transition(()-> gamepad2.left_trigger >0.2, State.RETRACTED,()->{
                    new ResetCommand().schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawLooseToggleCommand().schedule();
                    }

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
                .transition(()-> gamepad1.right_trigger <=0.2 && gamepad1.left_trigger <=0.2, State.HORIZ_EXTENDED,()->{
                    new IntakeStopCommand().schedule();
                })

                // INTAKING
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
//                    new SequentialCommandGroup(
////                    new Level2HangCommand(),
//                    new TiltCommand()
//                    ).schedule();
//                })
//                .transition(()-> gamepad1.dpad_up, State.HANGING, ()->{
//                })
//                .state(State.HANGING)
//                .onEnter(()->{
//                    new SequentialCommandGroup(
//                        new WaitCommand(1000),
//                        new HorizontalSlidesExtendCommand(),
//                        new VertSlidesStartCommand()
//                    ).schedule();
//                })

//                .transition(() -> gamepad1.dpad_down, State.HANG_RETRACTED, ()->{
//                    new VertSlidesHangDunkCommand().schedule();
//                })
//                .transition(() -> gamepad1.dpad_up, State.HANG_EXTENDED, ()->{
//
//                }
//
//                // RETRACTED HANG
//                .state(State.HANG_EXTENDED)
//                .onEnter(()->{
//                    new VertSlidesHangDunkCommand().schedule();
//                })
//                .transition(() -> gamepad1.dpad_up, State.HANG_RETRACTED, ()->{
//                    new VertSlidesHangAboveCommand().schedule();
//                })
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
        drivetrain.teleOpDrive(gamepad1);
        sm.update();
        telemetry.update();

        if(intakeColorSensor.spit){
            intake.spit();
        }
    }

}