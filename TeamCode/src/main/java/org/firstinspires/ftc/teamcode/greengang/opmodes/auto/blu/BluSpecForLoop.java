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
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawLooseCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristScoringSpecFlickCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.HighSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto.AutoSpecDunk;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto.HighSpecAutoCommand;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

@Autonomous(group = "paths")
public class BluSpecForLoop extends GreenLinearOpMode {
    public static TrajectoryVelocityConstraint FAST_VEL = SampleMecanumDrive.getVelocityConstraint(48, Math.toRadians(220), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint NORMAL_VEL = SampleMecanumDrive.getVelocityConstraint(41, Math.toRadians(180), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint SLOW_VEL = SampleMecanumDrive.getVelocityConstraint(18, Math.toRadians(150), DriveConstants.TRACK_WIDTH);

    public static TrajectoryAccelerationConstraint FAST_ACCEL = SampleMecanumDrive.getAccelerationConstraint(48);
    public static TrajectoryAccelerationConstraint NORMAL_ACCEL = SampleMecanumDrive.getAccelerationConstraint(40);
    public static TrajectoryAccelerationConstraint SLOW_ACCEL = SampleMecanumDrive.getAccelerationConstraint(20);

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
        addIntakeColorSensor();
        addTurret();
        addClawDistanceSensor();

        new OuttakeClawCloseCommand().schedule();
        new SpecIntakeCommand().schedule();

        startPose = new Pose2d(0, 64, Math.PI/2);

        TrajectorySequenceBuilder builder = drivetrain.trajectorySequenceBuilder(startPose)

                .setConstraints(NORMAL_VEL, NORMAL_ACCEL)

                .setTangent(-Math.PI/2)
                .addTemporalMarker(() -> {
                    new HighSpecAutoCommand().schedule();
                })
                .waitSeconds(0.25)

                .setTangent(-Math.PI/2)
                .splineToConstantHeading(new Vector2d(6, 26.5), -Math.PI/2)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new WaitCommand(200),
                            new VertSlidesHighSpecCommand(),
                            new OuttakeClawOpenCommand()
                    ).schedule();
                })

                .waitSeconds(0.5)
                .setTangent(Math.PI/2)

                .setConstraints(SLOW_VEL,SLOW_ACCEL)

                .addTemporalMarker(()->{
                    new SequentialCommandGroup(
                            new WaitCommand(1000),
                            new ResetCommand()
                    ).schedule();
                })

                .addTemporalMarker(() -> {
                    new SpecIntakeCommand().schedule();
                })

                //PUSHING SAMPLE 1
                .splineToConstantHeading(new Vector2d(1, 40), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-33, 37), Math.toRadians(180))

                .splineToConstantHeading(new Vector2d(-40, 16), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-47, 16), Math.toRadians(180))
                .setTangent(Math.PI/2)
                .splineToConstantHeading(new Vector2d(-47, 51), Math.toRadians(90))

                //PUSHING SAMPLE 2
                .splineToConstantHeading(new Vector2d(-47, 16), Math.toRadians(-90))
                .setTangent(Math.PI)
                .splineToConstantHeading(new Vector2d(-56, 16), Math.toRadians(180))
                .setTangent(Math.PI/2)
                .splineToConstantHeading(new Vector2d(-56, 51), Math.toRadians(90))

                //PUSHING SAMPLE 3
                .splineToConstantHeading(new Vector2d(-56, 16), Math.toRadians(-90))
                .setTangent(Math.PI)
                .splineToConstantHeading(new Vector2d(-63.5, 16), Math.toRadians(180))
                .setTangent(Math.PI/2)
                .splineToConstantHeading(new Vector2d(-63.5, 56.3), Math.toRadians(90));

                for (int i = 0; i < 4; i++) {

                    if (i > 0) {
                        builder.addTemporalMarker(() -> {
                            new SequentialCommandGroup(
                                    new OuttakeClawOpenCommand(),
                                    new WaitCommand(500),
                                    new SpecIntakeCommand()
                            ).schedule();
                        });
                    }

                    builder
                            .setTangent(Math.PI/2)
                            .splineToConstantHeading(new Vector2d(-48, 56 + (i == 0 ? 0.3 : 0)), (i == 0 ? 0 : Math.PI/2))
                            .splineToConstantHeading(new Vector2d(-48, 63.5 - (i > 1 ? 0.7 : 0)), Math.PI/2)
                            .waitSeconds(1)
                            .setTangent(-Math.PI/4)

                            .addTemporalMarker(() -> {
                                    new SequentialCommandGroup(
                                            new OuttakeClawCloseCommand(),
                                            new WaitCommand(300),
                                            new HighSpecAutoCommand()
                                    ).schedule();
                                })

                            .setConstraints(NORMAL_VEL, NORMAL_ACCEL)
                            .splineToConstantHeading(new Vector2d(4-2*i, 26.5), Math.toRadians(-90))
                            .addTemporalMarker(()->{
                                new SequentialCommandGroup(
                                        new VertSlidesHighSpecCommand(),
                                        new ClawWristScoringSpecFlickCommand()
                                ).schedule();
                            })
                            .waitSeconds(0.3);
                }

                builder
                        .addTemporalMarker(()-> {
                            new SequentialCommandGroup(
                                    new OuttakeClawOpenCommand(),
                                    new WaitCommand(1000),
                                    new ResetCommand()
                            ).schedule();
                        })
                        .setTangent(Math.PI/2)
                        .splineToConstantHeading(new Vector2d(-63.5, 56.3), Math.toRadians(90));
                farBlue = builder.build();
    }

    @Override
    public void initLoop() {
        if(stickyG1.b){
            new SequentialCommandGroup(
                    new ClawArmInSpecCommand(),
                    new ClawWristInSpecTransferCommand()
            ).schedule();
        }
        outtakeClaw.close();
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
    }

    public void telemetry(Telemetry tele){
        tele.addData("pose ", drivetrain.getPoseEstimate());
        tele.addData("ppl pose ", drivetrain.ppl.getPoseEstimate());
    }

}
