package org.firstinspires.ftc.teamcode.greengang.opmodes.auto.blu;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.greengang.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.auto.AutoSamplePart1;
import org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesExtendFullyCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesExtendHalfwayCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

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

        Pose2d startPose = new Pose2d(39.6, 65, Math.toRadians(180));
        drivetrain.setPoseEstimate(startPose);


        closeBlue = drivetrain.trajectorySequenceBuilder(startPose)

                .setTangent(Math.toRadians(-90))

                .addTemporalMarker(() -> {
                    new OuttakeClawCloseCommand().schedule();
                })

                // PRELOAD
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new WaitCommand(500), //original 1000
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(0)) // 57, 55 before
                .waitSeconds(0.2)//original 1

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand()).schedule();
                })
                .waitSeconds(.2)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ResetCommand()
                    ).schedule();
                })

//                SAMPLE 1
                .splineToLinearHeading(new Pose2d(48, 45, Math.toRadians(-90)), Math.toRadians(180)) //47 b4
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(3)//3.5

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(200),
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .waitSeconds(.75)//prev .75

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new WaitCommand(200),
                            new OuttakeClawOpenCommand()
                    ).schedule();
                })
                .waitSeconds(.2)


                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ResetCommand()
                    ).schedule();
                })

                //   SAMPLE 2
                .splineToLinearHeading(new Pose2d(57.8,45, Math.toRadians(-90)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(3)//3.5

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(200),
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .waitSeconds(0.75) //previous 0.75

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new WaitCommand(500),
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(600),
                            new ResetCommand()
                    ).schedule();
                })
                .waitSeconds(1)



                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(55,39.6, Math.toRadians(-45)), Math.toRadians(225)) //old: 55,39.6,-70,225
                //made sample 3 intaking time longer
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                        new IntakeInCommand(),
                        new HorizontalSlidesExtendHalfwayCommand(),
                        new WristDownCommand(),

                        new WaitCommand(300), // change?
                        new HorizontalSlidesExtendCommand(),
                        new WaitCommand(2000), //orignial 3000
                        new IntakeStopCommand(),
                        new TransferCommand()
                    ).schedule();
                })
                .waitSeconds(3)//3.5

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(200),
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .waitSeconds(.5)

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new WaitCommand(400),
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(400)

                    ).schedule();
                })
                .waitSeconds(.5)


                .addTemporalMarker(() -> {
                    new ResetCommand().schedule();

                })

                // PARK
                .splineToLinearHeading(new Pose2d(53,50, Math.toRadians(-90)), Math.toRadians(180))//200, 45
                .build();

    }

    @Override
    public void periodic() {


        if (!drivetrain.isBusy() && !done){
            drivetrain.followTrajectorySequenceAsync(closeBlue);
            done = true;
        }
        try {
            drivetrain.updateTrajectory();
        } catch (Exception e){
//            requestOpModeStop();
        }
    }
}