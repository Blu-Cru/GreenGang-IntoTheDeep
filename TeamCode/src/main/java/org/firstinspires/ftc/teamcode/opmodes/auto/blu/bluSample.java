package org.firstinspires.ftc.teamcode.opmodes.auto.blu;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart1;
import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart2;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "close blue auto", group = "paths")
public class bluSample extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(24, 64, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        TrajectorySequence closeBlue = drive.trajectorySequenceBuilder(startPose)
                .setTangent(-90)

                // PRELOAD
                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(45))
                .addTemporalMarker(() -> {
                    new AutoSamplePart2().schedule();
                })

                // SAMPLE 1
                .splineToLinearHeading(new Pose2d(47, 45, Math.toRadians(90)), Math.toRadians(145))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new AutoSamplePart2().schedule();
                })

                // SAMPLE 2
                .splineToLinearHeading(new Pose2d(58,45, Math.toRadians(90)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .splineToLinearHeading(new Pose2d(50,50, Math.toRadians(225)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new AutoSamplePart2().schedule();
                })

                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(56,42, Math.toRadians(-225)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .splineToLinearHeading(new Pose2d(50,50, Math.toRadians(225)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new AutoSamplePart2().schedule();
                })

                // PARK
                .splineToLinearHeading(new Pose2d(48,58, Math.toRadians(90)), Math.toRadians(45))
                .build();

        waitForStart();

        while(opModeIsActive()) {
            drive.followTrajectorySequenceAsync(closeBlue);
            drive.updateTrajectory();
            CommandScheduler.getInstance().run();
        }
    }
}
