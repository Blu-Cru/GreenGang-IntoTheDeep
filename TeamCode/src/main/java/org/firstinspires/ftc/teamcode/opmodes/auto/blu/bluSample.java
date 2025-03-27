package org.firstinspires.ftc.teamcode.opmodes.auto.blu;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart1;
import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart2;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmParkCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesExtendHalfwayCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.outtake.arm.ClawArm;

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

        Pose2d startPose = new Pose2d(39.6, 65, Math.toRadians(180));
        mecDrive = new SampleMecanumDrive(hardwareMap);
        mecDrive.setPoseEstimate(startPose);


        closeBlue = mecDrive.trajectorySequenceBuilder(startPose)

                .setTangent(Math.toRadians(-90))

                .addTemporalMarker(() -> {
                    new OuttakeClawCloseCommand().schedule();
                })

                // PRELOAD
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ScoringHighBucketCommand()
                    ).schedule();
                })
                .splineToLinearHeading(new Pose2d(53, 55, Math.toRadians(225)), Math.toRadians(0)) // 57, 55 before

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
                .splineToLinearHeading(new Pose2d(48.8, 45, Math.toRadians(-90)), Math.toRadians(180)) //47 b4
                .addTemporalMarker(() -> {
                    new AutoSamplePart1().schedule();
                })
                .waitSeconds(3)//3.5

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(50),
                            new VertSlidesStartCommand(),
                            new WaitCommand(200),
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
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(50),
                            new VertSlidesStartCommand(),
                            new WaitCommand(200),
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
                .splineToLinearHeading(new Pose2d(63,42, Math.toRadians(-60)), Math.toRadians(225)) //old: 55,39.6,-70,225
                //made sample 3 intaking time longer
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                        new SlidesLiftSlightlyCommand(),
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
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(50),
                            new VertSlidesStartCommand(),
                            new WaitCommand(200),
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
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new ClawWristIntakeCommand(),
                            new ClawArmIntakeCommand(),
                            new SlidesLiftSlightlyCommand(),

                            new ClawArmParkCommand()
                    ).schedule();
                })

                // PARK
                .setTangent(-90)
                .splineToLinearHeading(new Pose2d(19, 13, Math.toRadians(0)), Math.toRadians(180))
                .build();

    }
    @Override
    public void onStart() {
        Pose2d startPose = new Pose2d(39.6, 65, Math.toRadians(180));
        drivetrain.setPoseEstimate(startPose);

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