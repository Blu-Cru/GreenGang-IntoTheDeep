package org.firstinspires.ftc.teamcode.greengang.opmodes.auto.blu;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.greengang.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.auto.AutoSamplePart1;
import org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristFlatCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesExtendFullyCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesExtendHalfwayCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "4 Sample Wiggle", group = "paths")
public class bluSampleWiggle extends GreenLinearOpMode {
    public static TrajectoryVelocityConstraint FAST_VEL = SampleMecanumDrive.getVelocityConstraint(47, Math.toRadians(220), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint NORMAL_VEL = SampleMecanumDrive.getVelocityConstraint(41, Math.toRadians(180), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint SLOW_VEL = SampleMecanumDrive.getVelocityConstraint(15, Math.toRadians(150), DriveConstants.TRACK_WIDTH);

    public static TrajectoryAccelerationConstraint FAST_ACCEL = SampleMecanumDrive.getAccelerationConstraint(50);
    public static TrajectoryAccelerationConstraint NORMAL_ACCEL = SampleMecanumDrive.getAccelerationConstraint(40);
    public static TrajectoryAccelerationConstraint SLOW_ACCEL = SampleMecanumDrive.getAccelerationConstraint(15);

    public static TrajectoryVelocityConstraint[] velos = {SLOW_VEL, NORMAL_VEL, FAST_VEL};
    public static TrajectoryAccelerationConstraint[] accels = {SLOW_ACCEL, NORMAL_ACCEL, FAST_ACCEL};

    TrajectorySequence closeBlue;
    boolean done;
    Pose2d startPose;

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
        addTurret();

        startPose = new Pose2d(31, 57, Math.toRadians(180));
        drivetrain.setPoseEstimate(startPose);


        closeBlue = drivetrain.trajectorySequenceBuilder(startPose)
                .setConstraints(NORMAL_VEL,NORMAL_ACCEL)
                .setTangent(Math.toRadians(-90))

                .addTemporalMarker(() -> {
                    new OuttakeClawCloseCommand().schedule();
                })

                // PRELOAD
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new WaitCommand(500), //original 1000
                            new ScoringHighBucketCommand(),
                            new ClawWristBucketCommand()
                    ).schedule();
                })
                .splineToLinearHeading(new Pose2d(56, 55, Math.toRadians(225)), Math.toRadians(0)) // 57, 55 before
                .waitSeconds(0.1)//original 1

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(400),
                            new ClawWristTransferCommand(),
                            new ResetCommand()

                    ).schedule();

                })

//                SAMPLE 1
                .splineToLinearHeading(new Pose2d(53, 50, Math.toRadians(-90)), Math.toRadians(180)) //47 b4
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                        new AutoSamplePart1(),
                        new WaitCommand(1000),
                        new IntakeStopCommand()
                    ).schedule();

                })
                .waitSeconds(3)//3.5

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(200),
                            new ScoringHighBucketCommand(),
                            new ClawWristBucketCommand()


                    ).schedule();
                })
                .waitSeconds(.75)//prev .75

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(56, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new WaitCommand(200),
                            new OuttakeClawOpenCommand()
                    ).schedule();
                })
                .waitSeconds(.2)


                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ClawWristTransferCommand(),
                            new ResetCommand()
                    ).schedule();
                })

                //   SAMPLE 2
                .splineToLinearHeading(new Pose2d(62,50, Math.toRadians(-90)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new AutoSamplePart1(),
                            new WaitCommand(1000),
                            new IntakeStopCommand()
                    ).schedule();
                })
                .waitSeconds(3)//3.5

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(200),
                            new ScoringHighBucketCommand(),
                            new ClawWristBucketCommand()

                    ).schedule();
                })
                .waitSeconds(0.75) //previous 0.75

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(54, 54, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new WaitCommand(500),
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(600),
                            new ClawWristTransferCommand(),
                            new ResetCommand()
                    ).schedule();
                })
                .waitSeconds(1)



                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(55,50, Math.toRadians(-45)), Math.toRadians(135)) //old: 55,39.6,-70,225
                //made sample 3 intaking time longer
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                        new IntakeInCommand(),
                        new HorizontalSlidesExtendHalfwayCommand(),
                        new WristDownCommand(),

                        new WaitCommand(300), // change?
                        new HorizontalSlidesExtendCommand(),
                        new WaitCommand(2000), //orignial 3000
                        new TransferCommand(),
                        new WaitCommand(1000),
                        new IntakeStopCommand()
                    ).schedule();
                })
                .setConstraints(SLOW_VEL,SLOW_ACCEL)
                .splineToLinearHeading(new Pose2d(58,45, Math.toRadians(-80)), Math.toRadians(135)) //old: 55,39.6,-70,225
                .setConstraints(NORMAL_VEL,NORMAL_ACCEL)
//                .waitSeconds(0.5)
//                .splineToLinearHeading(new Pose2d(55,49.5, Math.toRadians(-55)), Math.toRadians(225)) //old: 55,39.6,-70,225
//                .waitSeconds(0.3)
//                .splineToLinearHeading(new Pose2d(55,49., Math.toRadians(-35)), Math.toRadians(225)) //old: 55,39.6,-70,225
//                .waitSeconds(0.3)
//                .splineToLinearHeading(new Pose2d(55,48.5, Math.toRadians(-55)), Math.toRadians(225)) //old: 55,39.6,-70,225
//                .waitSeconds(0.3)
//                .splineToLinearHeading(new Pose2d(55,48., Math.toRadians(-35)), Math.toRadians(225)) //old: 55,39.6,-70,225
//                .waitSeconds(0.3)
//                .splineToLinearHeading(new Pose2d(55,47.5, Math.toRadians(-55)), Math.toRadians(225)) //old: 55,39.6,-70,225
//                .waitSeconds(0.3)
//                .splineToLinearHeading(new Pose2d(55,47, Math.toRadians(-35)), Math.toRadians(225)) //old: 55,39.6,-70,225
//                .waitSeconds(0.3)
//                .splineToLinearHeading(new Pose2d(55,46.5, Math.toRadians(-55)), Math.toRadians(225)) //old: 55,39.6,-70,225
//                .waitSeconds(0.3)
//                .splineToLinearHeading(new Pose2d(55,46, Math.toRadians(-35)), Math.toRadians(225)) //old: 55,39.6,-70,225
//                .waitSeconds(0.3)
//                .splineToLinearHeading(new Pose2d(55,45.5, Math.toRadians(-55)), Math.toRadians(225)) //old: 55,39.6,-70,225


                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(200),
                            new ScoringHighBucketCommand(),
                            new ClawWristBucketCommand()

                    ).schedule();
                })
                .waitSeconds(.5)

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(53, 53, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new IntakeStopCommand(),
                            new WaitCommand(400),
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(400)

                    ).schedule();
                })
                .waitSeconds(.5)


                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new ClawWristFlatCommand(),
                            new ClawArmBucketCommand(),
                            new TurretInitCommand(),
                            new VertSlidesStartCommand()
                    ).schedule();
                    new SequentialCommandGroup(
                            new HorizontalSlidesExtendFullyCommand(),
                            new WaitCommand(500),
                            new HorizontalSlidesRetractCommand()
                    ).schedule();
                })
                .setTangent(-Math.PI/2)

                // PARK
                .splineToLinearHeading(new Pose2d(15,20, Math.toRadians(0)), Math.toRadians(180))//200, 45
                .build();

    }

    @Override
    public void periodic() {


        if (!drivetrain.isBusy() && !done){
            drivetrain.setPoseEstimate(startPose);
            drivetrain.ppl.setPoseEstimate(startPose);
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