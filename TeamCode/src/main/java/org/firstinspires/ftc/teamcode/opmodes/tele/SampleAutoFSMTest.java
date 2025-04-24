package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.common.commands.bucket.auto.AutoSamplePart1;
import org.firstinspires.ftc.teamcode.common.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.common.commands.intake.RetractAutoCommand;
import org.firstinspires.ftc.teamcode.common.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.common.pathbase.BlueSample;
import org.firstinspires.ftc.teamcode.common.pathbase.RedSample;
import org.firstinspires.ftc.teamcode.common.pathbase.SamplePath;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.common.subsystems.drive.DriveMode;

@TeleOp(name="sample auto fsm test", group ="TeleOp")

public class SampleAutoFSMTest extends GreenLinearOpMode{
    enum Type {
        BLUE_SAMPLE,
        RED_SAMPLE
    }
    double y, x, rx;
    enum State{
        INTAKING_SAMPLE,
        HIGH_BUCKET,
        TRANSFER,
        SCORED,
        DONE
    }
    StateMachine sm;
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

        if (gamepad1.left_bumper) {
            type = Type.BLUE_SAMPLE;
        } else if (gamepad1.right_bumper) {
            type = Type.RED_SAMPLE;
        }

        SamplePath sp;
        if (type.equals(Type.BLUE_SAMPLE)) {
            sp = new BlueSample();
        } else {
            sp = new RedSample();
        }

        sm = new StateMachineBuilder()

                .state(State.INTAKING_SAMPLE)
                .onEnter(()-> {
                    sp.getToSample(drivetrain.getPoseEstimate(), sampleNum).start();
                    new AutoSamplePart1().schedule();
                    sampleNum++;
                })
                .transition(()-> robot.color.isFull(), State.HIGH_BUCKET, ()-> {
                    new RetractAutoCommand().schedule();
                })

                // Sample auto; scoring high bucket
                // todo: confirm u can run traj this way + make more efficient
                .state(State.HIGH_BUCKET)
                .onEnter(()-> {
                        sp.getRetractFromHighBucket().start();
                        new WaitCommand(300);
                        new ScoringHighBucketCommand().schedule();
                        new WaitCommand(100);
                        sp.getToHighBucket().start();
                        new WaitCommand(100);
                        new OuttakeClawOpenCommand().schedule();
                })
                .transition(()-> robot.outtakeClaw.isOpen(), State.SCORED, ()-> {
                })

                // todo: how do u leave ts state
                .state(State.TRANSFER)
                .onEnter(()-> {
                    new TransferCommand().schedule();
                })

                .state(State.SCORED)
                .onEnter(()-> {
                    new ResetCommand().schedule();
                })
                .transition(()-> sampleNum>2, State.DONE, ()-> {
                    sp.getToPark().start();
                })
                .transition(()-> sampleNum<3, State.INTAKING_SAMPLE, ()-> {
//                    sp.getToSample(drivetrain.getPoseEstimate(), sampleNum);
                })

                .build();
        sm.setState(State.HIGH_BUCKET);
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
