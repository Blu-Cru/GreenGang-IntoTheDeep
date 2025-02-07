package org.firstinspires.ftc.teamcode.opmodes.auto.red;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart1;
import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart2;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "red sample auto", group = "paths")

public class redSample extends GreenLinearOpMode {
    TrajectorySequence closeRed;
    SampleMecanumDrive mecDrive;

    @Override
    public void initialize() {

        addDrivetrain();
        mecDrive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-24, -64, Math.toRadians(90));
        mecDrive.setPoseEstimate(startPose);

        closeRed = mecDrive.trajectorySequenceBuilder(startPose)
                // PRELOAD

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ScoringHighBucketCommand().schedule();
                })
                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(180))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new OuttakeClawOpenCommand().schedule();
                    new WaitCommand(2000).schedule(); // to be changed?
                })
                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ResetCommand().schedule();
                })

                // SAMPLE 1
                .splineToLinearHeading(new Pose2d(-47, -45, Math.toRadians(-90)), Math.toRadians(55))
                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ScoringHighBucketCommand().schedule();
                })
                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(180))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new OuttakeClawOpenCommand().schedule();
                    new WaitCommand(2000).schedule(); // to be changed?
                })
                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ResetCommand().schedule();
                })

                // SAMPLE 2
                .splineToLinearHeading(new Pose2d(-58, -45, Math.toRadians(-90)), Math.toRadians(0))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ScoringHighBucketCommand().schedule();
                })

                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(180))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new OuttakeClawOpenCommand().schedule();
                    new WaitCommand(2000).schedule(); // to be changed?
                })
                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ResetCommand().schedule();
                })

                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(-56, -42, Math.toRadians(-45)), Math.toRadians(0))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ScoringHighBucketCommand().schedule();
                })
                .splineToLinearHeading(new Pose2d(-50, -50, Math.toRadians(45)), Math.toRadians(180))

                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new OuttakeClawOpenCommand().schedule();
                    new WaitCommand(2000).schedule(); // to be changed?
                })
                .waitSeconds(5)
                .addTemporalMarker(() -> {
                    new ResetCommand().schedule();
                })

                // PARK
                .splineToLinearHeading(new Pose2d(-48, -58, Math.toRadians(-90)), Math.toRadians(-135))
                .build();

    }

    @Override
    public void periodic() {
        if (!drivetrain.isBusy()){
            this.mecDrive.followTrajectorySequenceAsync(closeRed);
        }
        try {
            this.mecDrive.updateTrajectory();
        } catch (Exception e){
            requestOpModeStop();
        }
        CommandScheduler.getInstance().run();
    }
}
