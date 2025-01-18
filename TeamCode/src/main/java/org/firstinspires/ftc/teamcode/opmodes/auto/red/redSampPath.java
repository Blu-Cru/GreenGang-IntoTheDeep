package org.firstinspires.ftc.teamcode.opmodes.auto.red;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart2;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "red sample path only", group = "paths")

public class redSampPath extends GreenLinearOpMode {

    TrajectorySequence closeRed;

    @Override
    public void initialize() {

        addDrivetrain();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-24, -64, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        TrajectorySequence closeRed = drive.trajectorySequenceBuilder(startPose)

                // PRELOAD
                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(180))

                //SAMPLE 1
                .splineToLinearHeading(new Pose2d(-50, -45, Math.toRadians(-90)), Math.toRadians(55))

                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(0))

                // SAMPLE 2
                .splineToLinearHeading(new Pose2d(-58, -45, Math.toRadians(-90)), Math.toRadians(0))

                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(0))

                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(-56, -42, Math.toRadians(-45)), Math.toRadians(0))

                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(0))


                // PARK
                .splineToLinearHeading(new Pose2d(-48, -58, Math.toRadians(-90)), Math.toRadians(-135))
                .build();

    }


    public void periodic() {
        drivetrain.followTrajectorySequenceAsync(closeRed);
        CommandScheduler.getInstance().run();
        drivetrain.updateTrajectory();
        drivetrain.updatePoseEstimate();
        Pose2d poseEstimate = drivetrain.getPoseEstimate();
        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.update();
    }

}
