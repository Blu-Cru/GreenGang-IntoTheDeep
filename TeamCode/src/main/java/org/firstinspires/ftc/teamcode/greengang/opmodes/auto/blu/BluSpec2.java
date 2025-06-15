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
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.HighSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto.AutoSpecDunk;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(group = "paths")
public class BluSpec2 extends GreenLinearOpMode {
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

        farBlue = drivetrain.trajectorySequenceBuilder(startPose)

                .setConstraints(NORMAL_VEL, NORMAL_ACCEL)

                .addTemporalMarker(() -> {
                    new HighSpecCommand().schedule();
                })
                .waitSeconds(0.25)
                .setTangent(-Math.PI/2)
                .splineToConstantHeading(new Vector2d(6, 26.5), -Math.PI/2)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new WaitCommand(200),
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

                .splineToConstantHeading(new Vector2d(1, 40), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-33, 37), Math.toRadians(180))

                .splineToConstantHeading(new Vector2d(-40, 17), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-47, 17), Math.toRadians(180))
                .setTangent(Math.PI/2)
                .splineToConstantHeading(new Vector2d(-47, 51), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-47, 17), Math.toRadians(90))
                .setTangent(Math.PI)
                .splineToConstantHeading(new Vector2d(-56, 17), Math.toRadians(90))

                .splineToConstantHeading(new Vector2d(-56, 57.3), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-56, 17), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-63.5, 17), Math.toRadians(90))

                //SPECIMEN 1
                .addTemporalMarker(() -> {
                    new SpecIntakeCommand().schedule();
                })
                .splineToConstantHeading(new Vector2d(-63.5, 56.3), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-48, 56.3), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-48, 63.5), Math.toRadians(90))


                .waitSeconds(1)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(300),
                            new HighSpecCommand()
                    ).schedule();
                })

                .setConstraints(NORMAL_VEL, NORMAL_ACCEL)
                .setTangent(-Math.PI/4)

                .splineToConstantHeading(new Vector2d(4, 26.5), Math.toRadians(-90))
                .addTemporalMarker(()->{
                    new ClawWristScoringSpecFlickCommand().schedule();
                })
                .waitSeconds(0.5)
                .setTangent(1/2.0*Math.PI)
                //SPECIMEN 2
                .addTemporalMarker(()-> {
                    new SequentialCommandGroup(
                        new OuttakeClawOpenCommand(),
                        new WaitCommand(500),
                        new SpecIntakeCommand()
                    ).schedule();
                })

                .splineToConstantHeading(new Vector2d(-48, 56), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-48, 63.5), Math.toRadians(90))
                .waitSeconds(1.1)


                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(300),
                            new HighSpecCommand()
                    ).schedule();
                })
                .setTangent(-Math.PI/4)
                .splineToConstantHeading(new Vector2d(2, 26.5), Math.toRadians(-90))
                .addTemporalMarker(()->{
                    new ClawWristScoringSpecFlickCommand().schedule();
                })
                .waitSeconds(0.3)
                //SPECIMEN 3
                .addTemporalMarker(()-> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(500),
                            new SpecIntakeCommand()
                    ).schedule();
                })
                .setTangent(1/2.0*Math.PI)

                .splineToConstantHeading(new Vector2d(-48, 56), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-48, 62.8), Math.toRadians(90))
                .waitSeconds(1.1)

                .setTangent(-Math.PI/4)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(300),
                            new HighSpecCommand()
                    ).schedule();
                })

                .splineToConstantHeading(new Vector2d(0, 26.5), Math.toRadians(-90))
                .waitSeconds(0.3)
                .addTemporalMarker(()->{
                    new ClawWristScoringSpecFlickCommand().schedule();
                })
                //SPECIMEN 4
                .addTemporalMarker(()-> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(500),
                            new SpecIntakeCommand()
                    ).schedule();
                })
                .setTangent(1/2.0*Math.PI)

                .splineToConstantHeading(new Vector2d(-48, 56), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-48, 62.8), Math.toRadians(90))
                .waitSeconds(1.1)

                .setTangent(-Math.PI/4)

                .addTemporalMarker(() -> {
                    new SequentialCommandGroup(
                            new OuttakeClawCloseCommand(),
                            new WaitCommand(300),
                            new HighSpecCommand()
                    ).schedule();
                })
                .splineToConstantHeading(new Vector2d(-2, 26.5), Math.toRadians(-90))

                .addTemporalMarker(()->{
                    new ClawWristScoringSpecFlickCommand().schedule();
                })

                .addTemporalMarker(()-> {
                    new SequentialCommandGroup(
                            new OuttakeClawOpenCommand(),
                            new WaitCommand(1000),
                            new ResetCommand()
                    ).schedule();
                })
                .setTangent(1/2.0*Math.PI)
                .splineToConstantHeading(new Vector2d(-52, 60), Math.toRadians(90))
                .build();
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
