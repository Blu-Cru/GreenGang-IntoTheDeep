package org.firstinspires.ftc.teamcode.greengang.opmodes.auto.blu;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.greengang.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto.AutoSpecDunk;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "blue spec auto", group = "paths")
public class bluSpec extends GreenLinearOpMode {
    public static TrajectoryVelocityConstraint FAST_VEL = SampleMecanumDrive.getVelocityConstraint(48, Math.toRadians(220), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint NORMAL_VEL = SampleMecanumDrive.getVelocityConstraint(41, Math.toRadians(180), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint SLOW_VEL = SampleMecanumDrive.getVelocityConstraint(25, Math.toRadians(150), DriveConstants.TRACK_WIDTH);

    public static TrajectoryAccelerationConstraint FAST_ACCEL = SampleMecanumDrive.getAccelerationConstraint(48);
    public static TrajectoryAccelerationConstraint NORMAL_ACCEL = SampleMecanumDrive.getAccelerationConstraint(40);
    public static TrajectoryAccelerationConstraint SLOW_ACCEL = SampleMecanumDrive.getAccelerationConstraint(30);

    public static TrajectoryVelocityConstraint[] velos = {SLOW_VEL, NORMAL_VEL, FAST_VEL};
    public static TrajectoryAccelerationConstraint[] accels = {SLOW_ACCEL, NORMAL_ACCEL, FAST_ACCEL};

    TrajectorySequence farBlue;
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

        Pose2d startPose = new Pose2d(-24, 64, Math.toRadians(90));
        drivetrain.setPoseEstimate(startPose);

        farBlue = drivetrain.trajectorySequenceBuilder(startPose)

                .setTangent(Math.toRadians(-90))
                .setConstraints(NORMAL_VEL, NORMAL_ACCEL)

                //PRELOAD SCORE
                        .addTemporalMarker(() -> {
                            new OuttakeClawCloseCommand();
                            new AutoSpecOuttake().schedule();
                        })

                .splineToLinearHeading(new Pose2d(-5, 36.5, Math.toRadians(90)), Math.toRadians(-90))//prev y: 36.7
                        .addTemporalMarker(() -> {
                            new SequentialCommandGroup(
                                    new WaitCommand(200),
                                    new AutoSpecDunk()
                            ).schedule();
                        })
                .waitSeconds(0.5)
                .setTangent(90)
                        .addTemporalMarker(() -> {
                            new ResetCommand().schedule();
                        })
                .splineToSplineHeading(new Pose2d(-35,26,Math.toRadians(-90)), Math.toRadians(-90))
                .setConstraints(SLOW_VEL,SLOW_ACCEL)

                .splineToConstantHeading(new Vector2d(-35, 19), Math.toRadians(-90))

                .splineToConstantHeading(new Vector2d(-47, 17), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-47, 51), Math.toRadians(90))

                //FINISHED PUSHING FIRST SAMPLE TO OBSERVATION ZONE

                .splineToConstantHeading(new Vector2d(-47, 17), Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(-58, 17), Math.toRadians(90))
                .addTemporalMarker(() -> {
                    new SpecIntakeCommand().schedule();
                })
                .splineToConstantHeading(new Vector2d(-58, 57.3), Math.toRadians(90))
                .setConstraints(FAST_VEL,FAST_ACCEL)
                //FINISHED PUSHING THIRD SAMPLE TO OBSERVATION ZONE
                //FIRST SPECIMEN INTAKE
//                .splineToConstantHeading(new Vector2d(-48,48), Math.toRadians(0))
//
//
//                .splineToConstantHeading(new Vector2d(-48, 57.3), Math.toRadians(90))
                .waitSeconds(0.8)

                .addTemporalMarker(() -> {

                            new OuttakeClawCloseCommand().schedule();
                        })
                .waitSeconds(0.3)

                .setTangent(-45)
                //FIRST SPECIMEN SCORE
                        .addTemporalMarker(() -> {
                            new AutoSpecOuttake().schedule();
                        })

                .splineToLinearHeading(new Pose2d(-6, 38, Math.toRadians(90)), Math.toRadians(-90))
                        .addTemporalMarker(() -> {
                            new AutoSpecDunk().schedule();
                        })

                //SECOND SPECIMEN INTAKE
                .waitSeconds(0.5)
                        .addTemporalMarker(() -> {
                            new SequentialCommandGroup(
                                    new WaitCommand(1000),
                                    new SpecIntakeCommand()

                            ).schedule();
                        })
                .setTangent(135)
                .splineToSplineHeading(new Pose2d(-48,56,Math.toRadians(-90)), Math.toRadians(90))

                .splineToConstantHeading(new Vector2d(-48, 60.3), Math.toRadians(90))

                .waitSeconds(0.8)
                .addTemporalMarker(() -> {
                            new OuttakeClawCloseCommand().schedule();
                        })
                .waitSeconds(0.3)

                .setTangent(-45)
                //SECOND SPECIMEN SCORE
                        .addTemporalMarker(() -> {
                            new AutoSpecOuttake().schedule();
                        })

                .splineToLinearHeading(new Pose2d(-7, 38.5, Math.toRadians(90)), Math.toRadians(-90))
                        .addTemporalMarker(() -> {
                            new AutoSpecDunk().schedule();
                        })

                //THIRD SPECIMEN INTAKE
                .waitSeconds(0.3)
                        .addTemporalMarker(() -> {
                            new SequentialCommandGroup(
                                    new WaitCommand(1000),
                                    new SpecIntakeCommand()
                            ).schedule();

                        })
                .setTangent(135)
                .splineToSplineHeading(new Pose2d(-48,57,Math.toRadians(-90)), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-48, 62.5), Math.toRadians(90))

                .waitSeconds(0.8)

                .addTemporalMarker(() -> {
                            new OuttakeClawCloseCommand().schedule();
                        })
                .waitSeconds(0.3)


                .setTangent(-45)
                //THIRD SPECIMEN SCORE
                        .addTemporalMarker(() -> {
                            new AutoSpecOuttake().schedule();
                        })

                .splineToLinearHeading(new Pose2d(-8, 40, Math.toRadians(90)), Math.toRadians(-90))
                        .addTemporalMarker(() -> {
                            new AutoSpecDunk().schedule();
                        })
                .waitSeconds(0.3)
                        .addTemporalMarker(() -> {
                            new SequentialCommandGroup(
                                    new WaitCommand(600),
                                    new ResetCommand()
                            ).schedule();

                        })
                .setTangent(135)
                //PARK
                .splineToSplineHeading(new Pose2d(-52,60,Math.toRadians(-90)), Math.toRadians(90))



                .build();

    }

    @Override
    public void initLoop() {
        outtakeClaw.close();
    }

    @Override
    public void periodic() {


        if (!drivetrain.isBusy() && !done){
            drivetrain.followTrajectorySequenceAsync(farBlue);
            done = true;
        }
        try {
            drivetrain.updateTrajectory();
        } catch (Exception e){
//            requestOpModeStop();
        }
    }


}
