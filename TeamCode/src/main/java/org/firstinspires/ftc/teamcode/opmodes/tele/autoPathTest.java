package org.firstinspires.ftc.teamcode.opmodes.tele;

import android.util.Log;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.common.commands.bucket.auto.AutoSamplePart1;
import org.firstinspires.ftc.teamcode.common.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.common.pathbase.BlueSampleTrajetories;
import org.firstinspires.ftc.teamcode.common.subsystems.drive.DriveMode;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@TeleOp(name="auto path test", group ="TeleOp")

public class autoPathTest extends GreenLinearOpMode{
    enum Type {
        BLUE_SAMPLE,
        RED_SAMPLE
    }
    double y, x, rx;
    enum State{
        INIT,
        INTAKING_SAMPLE,
        HIGH_BUCKET,
        SCORED,
        DONE
    }
    StateMachine sm;
    SampleMecanumDrive mecDrive;
    Type type;
    int sampleNum=0;
    BlueSampleTrajetories  sp;

    public void initialize(){
        Log.d("autoPathTest", "1");

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

        Log.d("autoPathTest", "2");

        mecDrive = new SampleMecanumDrive(hardwareMap);
        sp = new BlueSampleTrajetories(mecDrive);
//        if (type.equals(Type.BLUE_SAMPLE)) {
//            sp = new BlueSample();
//        } else {
//            sp = new RedSample();
//        }

        sm = new StateMachineBuilder()

                .state(State.INIT)
                .transition(this::opModeIsActive, State.HIGH_BUCKET)

                // Sample auto; scoring high bucket
                // todo: confirm u can run traj this way + make more efficient
                .state(State.HIGH_BUCKET)
                .onEnter(()-> {
                    mecDrive.followTrajectorySequenceAsync(sp.getToHighBucket());
                    mecDrive.updateTrajectory();
                })

                .transition(()-> !drivetrain.isBusy(), State.INTAKING_SAMPLE, ()-> {
                    sampleNum++;
                    mecDrive.updateTrajectory();
                })


                .state(State.INTAKING_SAMPLE)
                .onEnter(()-> {
                    mecDrive.followTrajectorySequenceAsync(sp.getToSample(mecDrive.getPoseEstimate(), sampleNum));
                    mecDrive.updateTrajectory();
                })

                .state(State.SCORED)
                .onEnter(()-> {

                })
                .transition(()-> sampleNum>3, State.DONE, ()-> {
                    mecDrive.followTrajectorySequenceAsync(sp.getToPark());
                    mecDrive.updateTrajectory();

                })
                .transition(()-> sampleNum<4, State.INTAKING_SAMPLE, ()-> {
                    mecDrive.updateTrajectory();
                })
                .state(State.DONE)

                .build();

        Log.d("autoPathTest", "3");
        sm.setState(State.INIT);
        sm.start();
        Log.d("autoPathTest", "4");

    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("Robot State: ", sm.getState());
        telemetry.addData("Sample Num:" , sampleNum);
    }

    @Override
    public void periodic(){

        Log.d("autoPathTest", ""+ sm.getState());

//        drivetrain.followTrajectorySequenceAsync(sp.getToHighBucket());
        sm.update();

        telemetry.addData("Robot State: ", sm.getState());
        telemetry.addData("Sample Num:" , sampleNum);
        telemetry.update();
        Log.d("autoPathTest", ""+sampleNum);

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
