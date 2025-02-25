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
//                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(-80)) // 225, 180 before



                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ScoringHighBucketCommand()).schedule();
                })
                .waitSeconds(3)
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand()).schedule();
                })
                .waitSeconds(3)
//                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ResetCommand()
                    ).schedule();
                })
                .waitSeconds(2)

//                SAMPLE 1
                .splineToLinearHeading(new Pose2d(49, 43, Math.toRadians(-90)), Math.toRadians(180)) //47 b4
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(3)

//                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180)) // 225, 180 before


                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(180)) // 225, 180 before
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new VertSlidesStartCommand(),
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(1000),
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .waitSeconds(3)
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(400)
                    ).schedule();
                })
//                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180)) // 225, 180 before

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ResetCommand()
                    ).schedule();
                })
                .waitSeconds(2)

              //   SAMPLE 2
                .splineToLinearHeading(new Pose2d(58,43, Math.toRadians(-90)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(3)
//                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180)) // 225, 180 before
//

                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(180)) // 225, 180 before
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new VertSlidesStartCommand(),
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(1000),
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .waitSeconds(3)
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(400)
                    ).schedule();
                })
//                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180)) // 225, 180 before

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ResetCommand()).schedule();
                })
                .waitSeconds(2)

                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(60,37, Math.toRadians(-45)), Math.toRadians(225))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(3)

//                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180)) // 225, 180 before

                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(180)) // 225, 180 before

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new VertSlidesStartCommand(),
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(1000),
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .waitSeconds(3)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(400)
                    ).schedule();
                })
//                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180)) // 225, 180 before

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ResetCommand(),
                            new VertSlidesStartCommand()
                    ).schedule();
                })
                .waitSeconds(2)
//
//                // PARK
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
