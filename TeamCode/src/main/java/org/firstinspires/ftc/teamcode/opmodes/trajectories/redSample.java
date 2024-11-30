package org.firstinspires.ftc.teamcode.opmodes.trajectories;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
                .waitSeconds(2)

                // SAMPLE 1
                .splineToLinearHeading(new Pose2d(-47, -45, Math.toRadians(-90)), Math.toRadians(55))
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(0))
                .waitSeconds(2)

                // SAMPLE 2
                .splineToLinearHeading(new Pose2d(-58,-45, Math.toRadians(-90)), Math.toRadians(0))
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(-50,-50, Math.toRadians(45)), Math.toRadians(0))
                .waitSeconds(2)

                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(-56,-42, Math.toRadians(-45)), Math.toRadians(0))
                .waitSeconds(2)
                .splineToLinearHeading(new Pose2d(-50,-50, Math.toRadians(45)), Math.toRadians(0))
                .waitSeconds(2)

                // SAMPLE 4
                .splineToLinearHeading(new Pose2d(-48,-58, Math.toRadians(-90)), Math.toRadians(-135))
                .waitSeconds(2)
                .build();

        waitForStart();

        while(opModeIsActive()) {
            drive.followTrajectorySequenceAsync(closeRed);
            drive.updateTrajectory();
        }
    }
}
