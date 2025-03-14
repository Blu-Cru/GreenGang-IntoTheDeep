package org.firstinspires.ftc.teamcode.opmodes.auto.blu;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecIntake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecOuttake2;
import org.firstinspires.ftc.teamcode.commands.spec.auto.SamplePassThroughCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "blue spec auto", group = "paths")
public class bluSpec extends GreenLinearOpMode {

    TrajectorySequence farBlue;
    SampleMecanumDrive mecDrive;
    boolean done;

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

        mecDrive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-24, 64, Math.toRadians(90));
        mecDrive.setPoseEstimate(startPose);

        farBlue = mecDrive.trajectorySequenceBuilder(startPose)
    .setTangent(Math.toRadians(-90))
    // PRELOAD PLACEMENT
                .addTemporalMarker(() -> {
                    new OuttakeClawCloseCommand().schedule();
                })
    .splineToLinearHeading(new Pose2d(-5, 42, Math.toRadians(90)), Math.toRadians(-90))

                .addTemporalMarker(() -> {
                    new AutoSpecOuttake().schedule();
                })
                .waitSeconds(2.0)

    .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(-90))
                .waitSeconds(1.0)
                .addTemporalMarker(() -> {
                    new AutoSpecOuttake2().schedule();
                })
                .waitSeconds(0.5)
    .splineToLinearHeading(new Pose2d(-5, 32, Math.toRadians(90)), Math.toRadians(-90))
                .waitSeconds(2.0)


                .setTangent(90)
                .addTemporalMarker(() -> {
                    new WaitCommand(1000);
                    new ResetCommand().schedule();
                })
    .splineToLinearHeading(new Pose2d(-35, 26, Math.toRadians(-90)), Math.toRadians(-90))

    .strafeTo(new Vector2d(-35,13))
//



    .strafeTo(new Vector2d(-45,13))
    .waitSeconds(0.5)
    .setTangent(90)

    .strafeTo(new Vector2d(-45,55))
    //pushed first sample to observation zone
    .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(90))
    //going to intake block from human player
        .addTemporalMarker(() -> {
            new AutoSpecIntake().schedule();
        })
                .waitSeconds(4.0)

    .setTangent(0)
    .splineToLinearHeading(new Pose2d(-5, 42, Math.toRadians(90)), Math.toRadians(-90))
    //outtake
    .setTangent(-90)

    .splineToLinearHeading(new Pose2d(-45, 13, Math.toRadians(-90)), Math.toRadians(-90))
//                .addTemporalMarker(() -> {
//                    new SamplePassThroughCommand().schedule();
//                })
//                        .strafeTo(new Vector2d(-45,13))

    .strafeTo(new Vector2d(-56,13))
    .waitSeconds(0.5)
    .setTangent(90)

    .strafeTo(new Vector2d(-56,55))
    .waitSeconds(1)
    //pushed first sample to observation zone
    .setTangent(0)
    .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(90))
    //going to intake block from human player
    .setTangent(0)
    .splineToLinearHeading(new Pose2d(-5, 42, Math.toRadians(90)), Math.toRadians(-90))
    //outtake
    .setTangent(-90)
    .splineToLinearHeading(new Pose2d(-56, 13, Math.toRadians(-90)), Math.toRadians(-90))

//                .addTemporalMarker(() -> {
//                    new SamplePassThroughCommand().schedule();
//                })
    // SPEC 5 PLACEMENT

    .strafeTo(new Vector2d(-65,13))
//                        .splineToLinearHeading(new Pose2d(-65, 13, Math.toRadians(-90)), Math.toRadians(180))
    .waitSeconds(0.5)
    .setTangent(90)
    .strafeTo(new Vector2d(-65,55))
//                        .splineToLinearHeading(new Pose2d(-65, 55, Math.toRadians(-90)), Math.toRadians(90))
    .waitSeconds(1)
    .setTangent(0)
    .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(90))
    //going to intake block from human player
    .setTangent(0)
    .splineToLinearHeading(new Pose2d(-5, 42, Math.toRadians(90)), Math.toRadians(-90))
    //outtake

//                .addTemporalMarker(() -> {
//                    new SamplePassThroughCommand().schedule();
//                })

                        // intake sample from human player
//                .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
//                .addTemporalMarker(() -> {
//                    new AutoSpecIntake().schedule();
//                })

                        // outtake sample
//                .splineToLinearHeading(new Pose2d(-1, 42, Math.toRadians(90)), Math.toRadians(-90))
//                .addTemporalMarker(() -> {
//                    new AutoSpecOuttake().schedule();
//                })



//                .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
//                .addTemporalMarker(() -> {
//                    new AutoSpecIntake().schedule();
//                })

//                .splineToLinearHeading(new Pose2d(1, 42, Math.toRadians(90)), Math.toRadians(-90))
//                .addTemporalMarker(() -> {
//                    new AutoSpecOuttake().schedule();
//                })



//                .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
//                .addTemporalMarker(() -> {
//                    new AutoSpecIntake().schedule();
//                })

//                .splineToLinearHeading(new Pose2d(3, 42, Math.toRadians(90)), Math.toRadians(-90))
//                .addTemporalMarker(() -> {
//                    new AutoSpecOuttake().schedule();
//                })



                        .build();


    }

    @Override
    public void periodic() {


        if (!drivetrain.isBusy() && !done){
            mecDrive.followTrajectorySequenceAsync(farBlue);
            done = true;
        }
        try {
            mecDrive.updateTrajectory();
        } catch (Exception e){
//            requestOpModeStop();
        }
    }


}
