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
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawLooseToggleCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristHighOutSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristScoringSpecToggleCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesExtendFullyCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawToggleCommand;
//import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.Level;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretFlipCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretTurn90Command;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.HighSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.LowSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.greengang.common.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.greengang.common.subsystems.slides.HorizontalSlides;
import org.firstinspires.ftc.teamcode.greengang.common.util.Globals;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;

@TeleOp(name="Main FSM", group ="TeleOp")

public class MainFSM extends GreenLinearOpMode {
    double y, x, rx;
    double hsPow, wristRotationPow;
    double hangPow;
    boolean hanging;
    enum State{
        DEFAULT,
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
        intakeColorSensor.startReading();
        hang.pmo.disengagePTO();

        sm = new StateMachineBuilder()

                // RETRACTED
                .state(State.DEFAULT)
                .transition(()-> Math.abs(-gamepad2.left_stick_y) > 0.1, State.HORIZ_EXTENDED)
                .transition(()-> stickyG2.dpad_up || stickyG1.dpad_up, State.SPEC_INTAKE,()->{
                    new SequentialCommandGroup(
                            new ClawWristInSpecTransferCommand(),
                            new SpecIntakeCommand(),
                            new ClawWristInSpecCommand()
                    ).schedule();
                })
//                .transition(()-> stickyG1.left_bumper, State.HIGH_SPEC,()->{
//                    new LowSpecCommand().schedule();
//                })
//                .transition(()-> stickyG1.right_bumper, State.LOW_BUCKET,()->{
//                    new HighSpecCommand().schedule();
//                })
                .transition(()-> stickyG2.left_bumper, State.LOW_BUCKET,()->{
                    new SequentialCommandGroup(
                            new ScoringLowBucketCommand(),
                            new ClawWristBucketCommand()
                    ).schedule();
                })
                .transition(()-> stickyG2.right_bumper, State.HIGH_BUCKET,()->{
                    new SequentialCommandGroup(
                            new ScoringHighBucketCommand(),
                            new ClawWristBucketCommand()
                    ).schedule();

                })
                .transition(()-> stickyG2.dpad_down, State.HORIZ_EXTENDED,()->{
                    new HorizontalSlidesExtendFullyCommand().schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }

                    hsPow = -gamepad2.left_stick_y;
                    if (Math.abs(hsPow) > .1){
                        horizontalSlides.manualSlide(hsPow);}

                })
                .transition(()-> gamepad2.left_trigger > 0.2 || gamepad1.left_trigger > 0.2, State.HORIZ_EXTENDED, ()->{
                    new IntakeInCommand().schedule();
                })
                .transition(()-> gamepad2.right_trigger > 0.2 || gamepad1.right_trigger > 0.2, State.HORIZ_EXTENDED,()-> {
                    new IntakeSpitCommand().schedule();
                })
                .transition(()-> gamepad2.right_trigger<.2 && gamepad1.right_trigger<.2 &&
                        gamepad1.left_trigger<.2 && gamepad2.left_trigger<.2, State.DEFAULT,()->{
                    new IntakeStopCommand().schedule();
                })

                //LOW BUCKET
                .state(State.LOW_BUCKET)
                .transition(()-> gamepad2.y, State.DEFAULT,()->{
                    new SequentialCommandGroup(
                            new ClawWristInSpecTransferCommand(),
                            new ResetCommand(),
                            new ClawWristTransferCommand()
                    ).schedule();
                })
                .transition(()-> gamepad2.right_bumper, State.HIGH_BUCKET,()->{
                    new SequentialCommandGroup(
                            new ScoringHighBucketCommand(),
                            new ClawWristBucketCommand()
                    ).schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }
                    if(stickyG1.dpad_right || stickyG2.dpad_right){
                        new TurretTurn90Command().schedule();
                    }
                    if(stickyG1.dpad_left || stickyG2.dpad_left){
                        new TurretFlipCommand().schedule();
                    }
                    wristRotationPow = -gamepad2.left_stick_y;
                    if (Math.abs(wristRotationPow) > 0.1) {
                        robot.turret.manualRotate(wristRotationPow / 500);
                    }
                })

                //HIGH BUCKET
                .state(State.HIGH_BUCKET)
                .transition(()-> gamepad2.y, State.DEFAULT,()->{
                    new SequentialCommandGroup(
                            new ClawWristInSpecTransferCommand(),
                            new ResetCommand(),
                            new ClawWristTransferCommand()
                    ).schedule();
                })
                .transition(()-> gamepad2.left_bumper, State.LOW_BUCKET,()->{
                    new SequentialCommandGroup(
                            new ScoringLowBucketCommand(),
                            new ClawWristBucketCommand()
                    ).schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }
                    if(stickyG1.dpad_right || stickyG2.dpad_right){
                        new TurretTurn90Command().schedule();
                    }
                    if(stickyG1.dpad_left || stickyG2.dpad_left){
                        new TurretFlipCommand().schedule();
                    }
                    wristRotationPow = -gamepad2.left_stick_y;
                    if (Math.abs(wristRotationPow) > 0.1) {
                        robot.turret.manualRotate(wristRotationPow / 500);
                    }
                })

                //HIGH SPECIMEN
                .state(State.HIGH_SPEC)
                .transition(()-> gamepad2.y, State.DEFAULT,()->{
                    new SequentialCommandGroup(
                            new ClawWristInSpecTransferCommand(),
                            new ResetCommand(),
                            new ClawWristTransferCommand()
                    ).schedule();
                })
                .transition(()-> stickyG2.dpad_up || stickyG1.dpad_up, State.SPEC_INTAKE, ()->{
                    new SequentialCommandGroup(
                            new ClawWristInSpecTransferCommand(),
                            new SpecIntakeCommand(),
                            new ClawWristInSpecCommand()
                    ).schedule();
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

                .state(State.LOW_SPEC)
                .transition(()-> gamepad2.y, State.DEFAULT,()->{
                    new SequentialCommandGroup(
                            new ClawWristInSpecTransferCommand(),
                            new ResetCommand(),
                            new ClawWristTransferCommand()
                    ).schedule();
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
                .transition(()-> stickyG1.dpad_up || stickyG2.dpad_up, State.SPEC_INTAKE, ()->{
                    new SequentialCommandGroup(
                            new ClawWristInSpecTransferCommand(),
                            new SpecIntakeCommand(),
                            new ClawWristInSpecCommand()
                    ).schedule();
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
                    new ClawWristHighOutSpecCommand().schedule();
                    new HighSpecCommand().schedule();
                })
                .transition(()-> gamepad2.y, State.DEFAULT,()->{
                    new SequentialCommandGroup(
                            new ClawWristInSpecTransferCommand(),
                            new ResetCommand(),
                            new ClawWristTransferCommand()
                    ).schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }
                })

                //HORIZ EXTENDED
                .state(State.HORIZ_EXTENDED)
                .loop(()->{
                    hsPow = -gamepad2.left_stick_y;
                    if (Math.abs(hsPow) > .1){
                        horizontalSlides.manualSlide(hsPow);}

                    if(gamepad1.right_trigger>.2 || gamepad2.right_trigger>.2){
                        new IntakeSpitCommand().schedule();
                    } else if(gamepad2.left_trigger>.2 || gamepad1.left_trigger>.2){
                        new WristDownCommand().schedule();
                        new IntakeInCommand().schedule();
                    } else {
                        new WristParallelCommand().schedule();
                        new IntakeStopCommand().schedule();
                    }
                })
                .transition(()-> horizontalSlides.loc == HorizontalSlides.LOC.RETRACTED, State.DEFAULT)
                .transition(()-> gamepad2.dpad_down, State.DEFAULT,()->{
                    new HorizontalSlidesRetractCommand().schedule();
                })
                .transition(()-> gamepad2.y, State.DEFAULT,()->{
                    new SequentialCommandGroup(
                            new ClawWristInSpecTransferCommand(),
                            new ResetCommand(),
                            new ClawWristTransferCommand()
                    ).schedule();
                })
                .transition(()-> stickyG2.left_bumper, State.LOW_BUCKET,()->{
                    new SequentialCommandGroup(
                            new ScoringLowBucketCommand(),
                            new ClawWristBucketCommand()
                    ).schedule();
                })
                .transition(()-> stickyG2.right_bumper, State.HIGH_BUCKET,()->{
                    new SequentialCommandGroup(
                            new ScoringHighBucketCommand(),
                            new ClawWristBucketCommand()
                    ).schedule();
                })
                .loop(()->{
                    if(stickyG1.a || stickyG2.a){
                        new OuttakeClawToggleCommand().schedule();
                    }
                })

                .build();

        sm.setState(State.DEFAULT);
        sm.start();
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("Robot State: ", sm.getState());
        telemetry.update();
    }

    @Override
    public void periodic(){
        drivetrain.teleOpDrive(gamepad1);

        if(gamepad1.right_stick_button) {
            drivetrain.setHeading(Math.PI/2);
            gamepad1.rumble(150);
        }

        sm.update();
        telemetry.update();

        if(intakeColorSensor.spit){
            intake.spit();
        }

        if (horizontalSlides.state == HorizontalSlides.STATE.IDLE) {
            horizontalSlides.pidTo(horizontalSlides.minpos);
        }
    }

}