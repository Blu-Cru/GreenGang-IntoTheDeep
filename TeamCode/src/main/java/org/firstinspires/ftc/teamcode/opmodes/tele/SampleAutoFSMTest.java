package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.common.commands.bucket.auto.AutoSamplePart1;
import org.firstinspires.ftc.teamcode.common.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.VertSlidesHighBucketCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.common.commands.intake.RetractAutoCommand;
import org.firstinspires.ftc.teamcode.common.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.common.pathbase.BlueSampleTrajetories;
import org.firstinspires.ftc.teamcode.common.subsystems.Robot;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.common.subsystems.drive.DriveMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@TeleOp(name="sample auto fsm test", group ="TeleOp")

public class SampleAutoFSMTest extends GreenLinearOpMode{
    enum Type {
        BLUE_SAMPLE,
        RED_SAMPLE
    }
    double y, x, rx;
    enum State{
        INIT,
        INTAKING_SAMPLE,
        HIGH_BUCKET,
        TRANSFER,
        SCORED,
        DONE
    }
    StateMachine sm;
    SampleMecanumDrive mecDrive;
    Type type;
    int sampleNum=0;

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
        outtakeClaw.close();
        type = Type.BLUE_SAMPLE;
//        if (gamepad1.left_bumper) {
//            type = Type.BLUE_SAMPLE;
//        } else if (gamepad1.right_bumper) {
//            type = Type.RED_SAMPLE;
//        }
        mecDrive = new SampleMecanumDrive(hardwareMap);
        BlueSampleTrajetories  sp = new BlueSampleTrajetories(mecDrive);
//        if (type.equals(Type.BLUE_SAMPLE)) {
//            sp = new BlueSample();
//        } else {
//            sp = new RedSample();
//        }

        sm = new StateMachineBuilder()
                .state(State.INIT)
                .transition(() -> this.opModeIsActive(), State.HIGH_BUCKET)
                // Sample auto; scoring high bucket
                // todo: confirm u can run traj this way + make more efficient
                .state(State.HIGH_BUCKET)
                .onEnter(()-> {
//                    drivetrain.followTrajectorySequenceAsync(sp.getRetractFromHighBucket()); no need for two paths

                    drivetrain.followTrajectorySequenceAsync(sp.getToHighBucket());

                    new SequentialCommandGroup(
                        new OuttakeClawCloseCommand(),
                        new WaitCommand(300),
                        new ScoringHighBucketCommand(),
                        new WaitCommand(3000),
                        new OuttakeClawOpenCommand()
                    ).schedule();
                })
                .transition(()-> robot.outtakeClaw.isOpen(), State.SCORED, ()-> {
                    sampleNum++;
                })

                .state(State.INTAKING_SAMPLE)
                .onEnter(()-> {
                    drivetrain.followTrajectorySequenceAsync(sp.getRetractFromHighBucket());
                    sp.getToSample(mecDrive.getPoseEstimate(), sampleNum).start();
                    new AutoSamplePart1().schedule();
                })
                .transition(()-> robot.color.isFull(), State.HIGH_BUCKET, ()-> {
                    new ResetCommand().schedule();
                    new WaitCommand(1000).schedule();
                    new OuttakeClawCloseCommand().schedule();
                })
                .transition(()-> !drivetrain.isBusy(), State.HIGH_BUCKET, ()-> {
                    new ResetCommand().schedule();
                    new WaitCommand(1000).schedule();
                    new OuttakeClawCloseCommand().schedule();
                })



                // todo: how do u leave ts state
                .state(State.TRANSFER)
                .onEnter(()-> {
                    new TransferCommand().schedule();
                })

                .state(State.SCORED)
                .onEnter(()-> {
                    new WaitCommand(500).schedule();
                    new ResetCommand().schedule();
                })
                .transition(()-> sampleNum>3, State.DONE, ()-> {
                    drivetrain.followTrajectorySequenceAsync(sp.getToPark());
                })
                .transition(()-> sampleNum<4, State.INTAKING_SAMPLE, ()-> {
//                    sp.getToSample(drivetrain.getPoseEstimate(), sampleNum);
                })
                .state(State.DONE)
                .build();
        sm.setState(State.INIT);
        sm.start();
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("Robot State: ", sm.getState());
    }

    @Override
    public void periodic(){
        intakeColorSensor.startReading();
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
