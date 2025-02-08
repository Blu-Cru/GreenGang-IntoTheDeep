package org.firstinspires.ftc.teamcode.opmodes.auto.blu;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
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
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;

@Autonomous(name = "close blue auto", group = "paths")
public class bluSample extends GreenLinearOpMode {
    TrajectorySequence closeBlue;
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

        Pose2d startPose = new Pose2d(24, 64, Math.toRadians(90));
        drivetrain.setPoseEstimate(startPose);

        closeBlue = drivetrain.trajectorySequenceBuilder(startPose)
                .setTangent(-90)

                // PRELOAD

                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180)) // 225, 180 before
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ScoringHighBucketCommand(),
                            new WaitCommand(2000),
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(2000),
                            new ResetCommand()
                    ).schedule();
                })
                .waitSeconds(4)
//
//                // SAMPLE 1
                .splineToLinearHeading(new Pose2d(47, 45, Math.toRadians(-90)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(1)

                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ScoringHighBucketCommand(),
                            new WaitCommand(2000),
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(2000),
                            new ResetCommand()
                    );
                })
                .waitSeconds(4)

                // SAMPLE 2
                .splineToLinearHeading(new Pose2d(58,45, Math.toRadians(-90)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(2)

                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ScoringHighBucketCommand(),
                            new WaitCommand(2000),
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(2000),
                            new ResetCommand()
                    );
                })
                .waitSeconds(4)

                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(56,42, Math.toRadians(135-180)), Math.toRadians(225))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(2)


                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ScoringHighBucketCommand(),
                            new WaitCommand(2000),
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(2000),
                            new ResetCommand()
                    );
                })
                .waitSeconds(4)

                // PARK
                .splineToLinearHeading(new Pose2d(48,58, Math.toRadians(90)), Math.toRadians(45))
                .build();

    }

    @Override
    public void periodic() {


        if (!drivetrain.isBusy() && !done){
            drivetrain.followTrajectorySequenceAsync(closeBlue);
            done = true;
        }
//        try {
        drivetrain.updateTrajectory();
//        } catch (Exception e){
//            requestOpModeStop();
//        }
        CommandScheduler.getInstance().run();
    }
}
