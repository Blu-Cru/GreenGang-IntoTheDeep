package org.firstinspires.ftc.teamcode.opmodes.auto.red;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart1;
import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart2;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "red sample auto", group = "paths")

public class redSample extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-24, -64, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        TrajectorySequence closeRed = drive.trajectorySequenceBuilder(startPose)

                // PRELOAD
                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new AutoSamplePart2().schedule();
                })

                // SAMPLE 1
                .splineToLinearHeading(new Pose2d(-47, -45, Math.toRadians(-90)), Math.toRadians(55))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new AutoSamplePart2().schedule();
                })

                // SAMPLE 2
                .splineToLinearHeading(new Pose2d(-58,-45, Math.toRadians(-90)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .splineToLinearHeading(new Pose2d(-50,-50, Math.toRadians(45)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new AutoSamplePart2().schedule();
                })

                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(-56,-42, Math.toRadians(-45)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .splineToLinearHeading(new Pose2d(-50,-50, Math.toRadians(45)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new AutoSamplePart2().schedule();
                })

                // PARK
                .splineToLinearHeading(new Pose2d(-48,-58, Math.toRadians(-90)), Math.toRadians(-135))
                .build();

        waitForStart();

        while(opModeIsActive()) {
            drive.followTrajectorySequenceAsync(closeRed);
            CommandScheduler.getInstance().run();
            drive.updateTrajectory();

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
        }
    }
}
