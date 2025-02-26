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
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;

@Autonomous(name = "close blue auto", group = "paths")
public class bluSample extends GreenLinearOpMode {
    TrajectorySequence closeBlue;
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

        Pose2d startPose = new Pose2d(24, 65, Math.toRadians(180));
        mecDrive = new SampleMecanumDrive(hardwareMap);
        mecDrive.setPoseEstimate(startPose);


        closeBlue = mecDrive.trajectorySequenceBuilder(startPose)

                .addTemporalMarker(() -> {
                    new OuttakeClawCloseCommand().schedule();
                })

                // PRELOAD
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new WaitCommand(2000),
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before
                .waitSeconds(1)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand()).schedule();
                })
                .waitSeconds(3)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ResetCommand()
                    ).schedule();
                })
//                SAMPLE 1
                .splineToLinearHeading(new Pose2d(49, 43, Math.toRadians(-90)), Math.toRadians(180)) //47 b4
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(3)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new VertSlidesStartCommand(),
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(3000),
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before
                .waitSeconds(1)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(400)
                    ).schedule();
                })

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ResetCommand()
                    ).schedule();
                })

              //   SAMPLE 2
                .splineToLinearHeading(new Pose2d(58,43, Math.toRadians(-90)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(3)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new VertSlidesStartCommand(),
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(3000),
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before
                .waitSeconds(1)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(400)
                    ).schedule();
                })

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ResetCommand()).schedule();
                })

                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(60,37, Math.toRadians(-45)), Math.toRadians(225))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(3)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new VertSlidesStartCommand(),
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(3000),
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before
                .waitSeconds(1)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(400)
                    ).schedule();
                })

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ResetCommand(),
                            new VertSlidesStartCommand()
                    ).schedule();
                })

                // PARK
                .splineToLinearHeading(new Pose2d(53,60, Math.toRadians(200)), Math.toRadians(45))
                .build();

    }

    @Override
    public void periodic() {


        if (!drivetrain.isBusy() && !done){
            mecDrive.followTrajectorySequenceAsync(closeBlue);
            done = true;
        }
        try {
            mecDrive.updateTrajectory();
        } catch (Exception e){
//            requestOpModeStop();
        }
    }
}
