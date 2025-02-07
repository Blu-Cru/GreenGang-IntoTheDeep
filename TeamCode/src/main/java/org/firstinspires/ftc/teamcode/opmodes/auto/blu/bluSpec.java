package org.firstinspires.ftc.teamcode.opmodes.auto.blu;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecIntake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.SamplePassThroughCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "blue spec auto", group = "paths")
public class bluSpec extends GreenLinearOpMode {

    TrajectorySequence farBlue;
    SampleMecanumDrive mecDrive;

    @Override
    public void initialize() {
        addDrivetrain();
        SampleMecanumDrive mecDrive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-24, 64, Math.toRadians(-90));
        mecDrive.setPoseEstimate(startPose);

        farBlue = mecDrive.trajectorySequenceBuilder(startPose)

                // PRELOAD PLACEMENT
                .splineToLinearHeading(new Pose2d(-5, 42, Math.toRadians(90)), Math.toRadians(-90))
                .addTemporalMarker(() -> {
                    new AutoSpecOuttake().schedule();
                })

                // PRELOAD FROM HUMAN PLACEMENT
                .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(90))
                .addTemporalMarker(() -> {
                    new AutoSpecIntake().schedule();
                })

                .splineToLinearHeading(new Pose2d(-3, 42, Math.toRadians(90)), Math.toRadians(-90))
                .addTemporalMarker(() -> {
                    new AutoSpecOuttake().schedule();
                })

                // SPEC 3 PLACEMENT
                .splineToLinearHeading(new Pose2d(-48, 45, Math.toRadians(-90)), Math.toRadians(180))

                // Give sample to human player
                .splineToLinearHeading(new Pose2d(-48, 50, Math.toRadians(-90)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new SamplePassThroughCommand().schedule();
                })

                // intake sample from human player
                .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new AutoSpecIntake().schedule();
                })

                // outtake sample
                .splineToLinearHeading(new Pose2d(-1, 42, Math.toRadians(90)), Math.toRadians(-90))
                .addTemporalMarker(() -> {
                    new AutoSpecOuttake().schedule();
                })

                // SPEC 4 PLACEMENT
                .splineToLinearHeading(new Pose2d(-58, 45, Math.toRadians(-90)), Math.toRadians(180))

                .splineToLinearHeading(new Pose2d(-48, 50, Math.toRadians(-90)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new SamplePassThroughCommand().schedule();
                })

                .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new AutoSpecIntake().schedule();
                })

                .splineToLinearHeading(new Pose2d(1, 42, Math.toRadians(90)), Math.toRadians(-90))
                .addTemporalMarker(() -> {
                    new AutoSpecOuttake().schedule();
                })

                // SPEC 5 PLACEMENT
                .splineToLinearHeading(new Pose2d(-56, 41, Math.toRadians(-135)), Math.toRadians(180))

                .splineToLinearHeading(new Pose2d(-48, 50, Math.toRadians(-90)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new SamplePassThroughCommand().schedule();
                })

                .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
                .addTemporalMarker(() -> {
                    new AutoSpecIntake().schedule();
                })

                .splineToLinearHeading(new Pose2d(3, 42, Math.toRadians(90)), Math.toRadians(-90))
                .addTemporalMarker(() -> {
                    new AutoSpecOuttake().schedule();
                })

                .build();

        }

    @Override
    public void periodic() {
        if (!drivetrain.isBusy()){
            this.mecDrive.followTrajectorySequenceAsync(farBlue);
        }
        try {
            this.mecDrive.updateTrajectory();
        } catch (Exception e){
            requestOpModeStop();
        }
        CommandScheduler.getInstance().run();
    }


}
