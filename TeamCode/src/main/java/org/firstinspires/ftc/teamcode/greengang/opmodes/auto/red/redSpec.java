package org.firstinspires.ftc.teamcode.greengang.opmodes.auto.red;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.SamplePassThroughCommand;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "red spec auto", group = "paths")
public class redSpec extends GreenLinearOpMode {
    TrajectorySequence farRed;
    @Override
    public void initialize() {

        addDrivetrain();
        Pose2d startPose = new Pose2d(24, -64, Math.toRadians(90));
        drivetrain.setPoseEstimate(startPose);

        farRed = drivetrain.trajectorySequenceBuilder(startPose)

                // PRELOAD PLACEMENT
                .splineToLinearHeading(new Pose2d(-5, -42, Math.toRadians(-90)), Math.toRadians(90))
                .addTemporalMarker(() -> {
                    new AutoSpecOuttake().schedule();
                })

                // PRELOAD FROM HUMAN PLACEMENT
                .splineToLinearHeading(new Pose2d(48, -60, Math.toRadians(90)), Math.toRadians(-90))
                .addTemporalMarker(() -> {
                    new SpecIntakeCommand().schedule();
                })

                .splineToLinearHeading(new Pose2d(-3, -42, Math.toRadians(-90)), Math.toRadians(90))
                .addTemporalMarker(() -> {
                    new AutoSpecOuttake().schedule();
                })

                // SPEC 3 PLACEMENT
                .splineToLinearHeading(new Pose2d(48, -45, Math.toRadians(90)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new SamplePassThroughCommand().schedule();
                })

                .splineToLinearHeading(new Pose2d(48, -50, Math.toRadians(90)), Math.toRadians(180))

                .splineToLinearHeading(new Pose2d(48, -60, Math.toRadians(90)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new SpecIntakeCommand().schedule();
                })

                .splineToLinearHeading(new Pose2d(-1, -42, Math.toRadians(-90)), Math.toRadians(90))
                .addTemporalMarker(() -> {
                    new AutoSpecOuttake().schedule();
                })

                // SPEC 4 PLACEMENT
                .splineToLinearHeading(new Pose2d(58, -45, Math.toRadians(90)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new SamplePassThroughCommand().schedule();
                })

                .splineToLinearHeading(new Pose2d(48, -50, Math.toRadians(90)), Math.toRadians(180))

                .splineToLinearHeading(new Pose2d(48, -60, Math.toRadians(90)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new SpecIntakeCommand().schedule();
                })

                .splineToLinearHeading(new Pose2d(1, -42, Math.toRadians(-90)), Math.toRadians(90))
                .addTemporalMarker(() -> {
                    new AutoSpecOuttake().schedule();
                })

                // SPEC 5 PLACEMENT
                .splineToLinearHeading(new Pose2d(56, -41, Math.toRadians(45)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new SamplePassThroughCommand().schedule();
                })

                .splineToLinearHeading(new Pose2d(48, -50, Math.toRadians(90)), Math.toRadians(180))


                .splineToLinearHeading(new Pose2d(48, -60, Math.toRadians(90)), Math.toRadians(180))
                .addTemporalMarker(() -> {
                    new SpecIntakeCommand().schedule();
                })

                .splineToLinearHeading(new Pose2d(3, -42, Math.toRadians(-90)), Math.toRadians(90))
                .addTemporalMarker(() -> {
                    new AutoSpecOuttake().schedule();
                })

                .build();

    }

    @Override
    public void periodic() {
        if (!drivetrain.isBusy()){
            drivetrain.followTrajectorySequenceAsync(farRed);
        }
        try {
            drivetrain.updateTrajectory();
        } catch (Exception e){
            requestOpModeStop();
        }
        CommandScheduler.getInstance().run();
    }
}
