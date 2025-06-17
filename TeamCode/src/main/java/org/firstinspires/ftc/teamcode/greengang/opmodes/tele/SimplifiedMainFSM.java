package org.firstinspires.ftc.teamcode.greengang.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.low.ScoringLowBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawLooseToggleCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawToggleCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristScoringSpecToggleCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesExtendFullyCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretFlipCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretTurn90Command;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.HighSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.LowSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.greengang.common.subsystems.slides.HorizontalSlides;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;

@TeleOp(name="Main FSM Simple", group ="TeleOp")

public class SimplifiedMainFSM extends GreenLinearOpMode {
    double hsPow, wristRotationPow;
    enum State{
        DEFAULT,
        LOW_BUCKET,
        HIGH_BUCKET,
        HIGH_SPEC,
        INTAKING,
        SPEC_INTAKE,
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

                .state(State.DEFAULT)
                .onEnter(()-> {
                    new ResetCommand().schedule();
                })
                .transition(()-> stickyG2.dpad_up, State.SPEC_INTAKE,()->{
                    new SpecIntakeCommand().schedule();
                })
                .transition(()-> stickyG2.left_bumper, State.LOW_BUCKET,()->{
                    new ScoringLowBucketCommand().schedule();
                })
                .transition(()-> stickyG2.right_bumper, State.HIGH_BUCKET,()->{
                    new ScoringHighBucketCommand().schedule();
                })
                .transition(()-> stickyG2.dpad_down, State.INTAKING,()->{
                    new HorizontalSlidesExtendFullyCommand().schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }

                    hsPow = -gamepad2.left_stick_y;
                    if (Math.abs(hsPow) > .1){
                        horizontalSlides.manualSlide(hsPow);}

                    if(gamepad1.left_trigger>.2){
                        new IntakeInCommand().schedule();
                    } else if (gamepad1.right_trigger>.2){
                        new IntakeSpitCommand().schedule();
                    }
                    else{
                        new WristParallelCommand().schedule();
                        new IntakeStopCommand().schedule();
                    }
                })

                .state(State.LOW_BUCKET)
                .transition(()-> gamepad2.left_trigger>0.2, State.DEFAULT,()->{
                    new ResetCommand().schedule();
                })
                .transition(()-> gamepad2.right_bumper, State.HIGH_BUCKET,()->{
                    new ScoringHighBucketCommand().schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawLooseToggleCommand().schedule();
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

                .state(State.HIGH_BUCKET)
                .transition(()-> gamepad2.left_trigger>0.2, State.DEFAULT,()->{
                    new ResetCommand().schedule();
                })
                .transition(()-> gamepad2.left_bumper, State.LOW_BUCKET,()->{
                    new ScoringLowBucketCommand().schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawLooseToggleCommand().schedule();
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
                .transition(()-> gamepad2.left_trigger>0.2, State.DEFAULT,()->{
                    new ResetCommand().schedule();
                })
                .transition(()-> stickyG2.dpad_up, State.SPEC_INTAKE, ()->{
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
                    if(stickyG1.b){
                        new ClawWristScoringSpecToggleCommand().schedule();
                    }

                })
                //LOW SPECIMEN
                .state(State.LOW_SPEC)
                .transition(()-> gamepad2.left_trigger>0.2, State.DEFAULT,()->{
                    new ResetCommand().schedule();
                })
                .transition(()-> stickyG2.dpad_up, State.SPEC_INTAKE, ()->{
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


                // SPECIMEN INTAKE
                .state(State.SPEC_INTAKE)
                .transition(()-> stickyG1.left_bumper, State.LOW_SPEC, ()->{
                    new LowSpecCommand().schedule();
                })
                .transition(()-> gamepad1.right_bumper, State.HIGH_SPEC,()->{
                    new HighSpecCommand().schedule();
                })
                .transition(()-> gamepad2.left_trigger >0.2, State.DEFAULT,()->{
                    new ResetCommand().schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawLooseToggleCommand().schedule();
                    }
                })

                //HORIZ EXTENDED/Intaking
                .state(State.INTAKING)
                .loop(()->{
                    hsPow = -gamepad2.left_stick_y;
                    if (Math.abs(hsPow) > .1){
                        horizontalSlides.manualSlide(hsPow);}

                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawLooseToggleCommand().schedule();
                    }

                    if(gamepad1.left_trigger>.2){
                        new WristDownCommand().schedule();
                        new IntakeInCommand().schedule();
                    } else if (gamepad1.right_trigger>.2){
                        new IntakeSpitCommand().schedule();
                    } else {
                        new WristParallelCommand().schedule();
                        new IntakeStopCommand().schedule();
                    }
                })
                .transition(()-> gamepad2.dpad_down, State.DEFAULT,()->{
                    new HorizontalSlidesRetractCommand().schedule();
                })
                .transition(()-> gamepad2.left_trigger > 0.2, State.DEFAULT,()->{
                    new ResetCommand().schedule();
                })
                .transition(()-> horizontalSlides.loc == HorizontalSlides.LOC.RETRACTED || horizontalSlides.position < 20, State.DEFAULT, ()->{})

                .build();
        sm.setState(State.DEFAULT);
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
        if(gamepad1.right_stick_button) {
            drivetrain.setHeading(Math.PI/2);
            gamepad1.rumble(150);
        }
        if(intakeColorSensor.spit){
            new IntakeSpitCommand().schedule();
        }

    }

}