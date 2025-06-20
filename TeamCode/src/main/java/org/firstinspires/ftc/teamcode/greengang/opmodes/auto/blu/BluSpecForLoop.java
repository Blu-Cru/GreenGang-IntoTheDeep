package org.firstinspires.ftc.teamcode.greengang.opmodes.auto.blu;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmFlatCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInspecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawLooseCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawToggleCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristFlatCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristHighOutSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristScoringSpecFlickCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesHighSpecAutoScoreCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.HighSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto.AutoSpecDunk;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto.HighSpecAutoCommand;
import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

@Autonomous(group = "paths")
public class BluSpecForLoop extends GreenLinearOpMode {
    public static TrajectoryVelocityConstraint FAST_VEL = SampleMecanumDrive.getVelocityConstraint(47, Math.toRadians(220), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint NORMAL_VEL = SampleMecanumDrive.getVelocityConstraint(41, Math.toRadians(180), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint SLOW_VEL = SampleMecanumDrive.getVelocityConstraint(27, Math.toRadians(150), DriveConstants.TRACK_WIDTH);

    public static TrajectoryAccelerationConstraint FAST_ACCEL = SampleMecanumDrive.getAccelerationConstraint(50);
    public static TrajectoryAccelerationConstraint NORMAL_ACCEL = SampleMecanumDrive.getAccelerationConstraint(40);
    public static TrajectoryAccelerationConstraint SLOW_ACCEL = SampleMecanumDrive.getAccelerationConstraint(28);

    public static TrajectoryVelocityConstraint[] velos = {SLOW_VEL, NORMAL_VEL, FAST_VEL};
    public static TrajectoryAccelerationConstraint[] accels = {SLOW_ACCEL, NORMAL_ACCEL, FAST_ACCEL};

    TrajectorySequence farBlue;
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

        turret.init();
        startPose = new Pose2d(0, 64, Math.PI/2);

        TrajectorySequenceBuilder builder = drivetrain.trajectorySequenceBuilder(startPose)

                .setConstraints(FAST_VEL, FAST_ACCEL)

                .setTangent(-Math.PI/2)
                .addTemporalMarker(() -> {
                    new ClawWristHighOutSpecCommand().schedule();
                    new HighSpecCommand().schedule();
                })
                .waitSeconds(1.0)
                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new WaitCommand(1400),
                            new ClawWristScoringSpecFlickCommand(),
                            new VertSlidesHighSpecAutoScoreCommand(),
                            new OuttakeClawOpenCommand()
                    ).schedule();
                })
                .splineToConstantHeading(new Vector2d(6, 24), -Math.PI/2)



                .setConstraints(SLOW_VEL,SLOW_ACCEL)

                .addTemporalMarker(()->{
                    new SequentialCommandGroup(
                            new WaitCommand(500),
                            new SpecIntakeCommand(),
                            new ClawWristInSpecCommand()
                    ).schedule();
                })


                .splineToConstantHeading(new Vector2d(1, 40), Math.toRadians(180))

                .splineToConstantHeading(new Vector2d(-33, 37), Math.toRadians(180))

                .splineToConstantHeading(new Vector2d(-40, 19), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-43, 19), Math.toRadians(90))
//                        .setTangent(Math.PI/2)
                .splineToConstantHeading(new Vector2d(-43, 50), Math.toRadians(90))

                //JUST FINISHED PUSHING SAMPLE 1
                .splineToConstantHeading(new Vector2d(-43, 19), Math.toRadians(225))
//                        .setTangent(Math.PI)
                .splineToConstantHeading(new Vector2d(-55, 19), Math.toRadians(90))
//                        .setTangent(Math.PI/2)
                .splineToConstantHeading(new Vector2d(-55, 51), Math.toRadians(90));

//                //JUST FINISHED PUSHING SAMPLE 2
//                .splineToConstantHeading(new Vector2d(-56, 18), Math.toRadians(225))
////                        .setTangent(Math.PI)
//                .splineToConstantHeading(new Vector2d(-62, 18), Math.toRadians(90))
//
////                        .setTangent(Math.PI/2)
//                .splineToConstantHeading(new Vector2d(-62, 49), Math.toRadians(90));


                for (int i = 0; i < 2; i++) {

                    if (i > 0) {
                        builder.addTemporalMarker(() -> {
                            new SequentialCommandGroup(
                                    new OuttakeClawOpenCommand(),
                                    new WaitCommand(500),
                                    new SpecIntakeCommand(),
                                    new ClawWristInSpecCommand()
                            ).schedule();
                        });
                    }

                            if(i>0){
                                builder
                                        .setTangent(Math.PI/2)
                                        .splineToConstantHeading(new Vector2d(4-2*i, 35), Math.toRadians(90));
                            }
                            builder.splineToConstantHeading(new Vector2d(-36, 58), (i == 0 ? 0 : Math.PI/2))
                            .setTangent(Math.PI/2)
                            .setConstraints(SLOW_VEL,SLOW_ACCEL)
                            .splineToConstantHeading(new Vector2d(-36, 62.4), Math.PI/2)

                            .waitSeconds(0.2)
                            .setTangent(-Math.PI/4)

                            .addTemporalMarker(() -> {
                                    new SequentialCommandGroup(
                                            new OuttakeClawCloseCommand(),
                                            new WaitCommand(300),
                                            new HighSpecCommand(),
                                            new ClawWristHighOutSpecCommand()


                                    ).schedule();
                                })
                                    .waitSeconds(0.5)

                            .setConstraints(FAST_VEL, FAST_ACCEL)
                            .splineToConstantHeading(new Vector2d(4-2*i, 26), Math.toRadians(-90))
                            .addTemporalMarker(()->{
                                new SequentialCommandGroup(
                                        new VertSlidesHighSpecAutoScoreCommand(),
                                        new ClawWristScoringSpecFlickCommand()
                                ).schedule();
                            });
                }

                builder
                        .addTemporalMarker(()-> {
                            new SequentialCommandGroup(
                                    new OuttakeClawOpenCommand(),
                                    new WaitCommand(1000),
                                    new ClawArmInitCommand(),
                                    new OuttakeClawOpenCommand(),
                                    new TurretInitCommand(),
                                    new ClawWristTransferCommand(),
                                    new WaitCommand(1000),
                                    new VertSlidesStartCommand()
                            ).schedule();
                        })
                        .setTangent(Math.PI/2)
                        .splineToLinearHeading(new Pose2d(-33, 56.3, Math.toRadians(-90)), Math.toRadians(90));
                farBlue = builder.build();
    }

    @Override
    public void initLoop() {

    }

    @Override
    public void periodic() {

        if (!drivetrain.isBusy() && !done){
            drivetrain.setPoseEstimate(startPose);
            drivetrain.ppl.setPoseEstimate(startPose);
            drivetrain.followTrajectorySequenceAsync(farBlue);
            done = true;
        }
        try {
            drivetrain.updateTrajectory();
        } catch (Exception e){
//            requestOpModeStop();
        }
        horizontalSlides.pidTo(horizontalSlides.minpos); // may need to remove
    }

    public void telemetry(Telemetry tele){
        tele.addData("pose ", drivetrain.getPoseEstimate());
        tele.addData("ppl pose ", drivetrain.ppl.getPoseEstimate());
    }

}
