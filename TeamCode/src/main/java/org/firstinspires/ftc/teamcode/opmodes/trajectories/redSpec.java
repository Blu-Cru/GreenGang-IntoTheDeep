package org.firstinspires.ftc.teamcode.opmodes.trajectories;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "red spec auto", group = "paths")
public class redSpec extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(24, -64, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        TrajectorySequence farRed = drive.trajectorySequenceBuilder(startPose)

                // PRELOAD PLACEMENT
                .splineToLinearHeading(new Pose2d(0, -42, Math.toRadians(-90)), Math.toRadians(90))

                // PRELOAD FROM HUMAN PLACEMENT
                .splineToLinearHeading(new Pose2d(48, -60, Math.toRadians(90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(0, -42, Math.toRadians(-90)), Math.toRadians(90))

                // SPEC 3 PLACEMENT
                .splineToLinearHeading(new Pose2d(48, -45, Math.toRadians(90)), Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(48, -50, Math.toRadians(-90)), Math.toRadians(180))
                .turn(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(48, -60, Math.toRadians(90)), Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(0, -42, Math.toRadians(-90)), Math.toRadians(90))

                // SPEC 4 PLACEMENT
                .splineToLinearHeading(new Pose2d(58, -45, Math.toRadians(90)), Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(48, -50, Math.toRadians(-90)), Math.toRadians(180))
                .turn(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(48, -60, Math.toRadians(90)), Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(0, -42, Math.toRadians(-90)), Math.toRadians(90))

                // SPEC 5 PLACEMENT
                .splineToLinearHeading(new Pose2d(56, -41, Math.toRadians(45)), Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(48, -50, Math.toRadians(-90)), Math.toRadians(180))
                .turn(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(48, -60, Math.toRadians(90)), Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(0, -42, Math.toRadians(-90)), Math.toRadians(90))

                .build();


        waitForStart();

        while(opModeIsActive()) {
            drive.followTrajectorySequenceAsync(farRed);
            drive.updateTrajectory();
        }
    }
}
