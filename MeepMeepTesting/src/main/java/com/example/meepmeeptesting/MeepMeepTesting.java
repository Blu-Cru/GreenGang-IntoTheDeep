package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Arrays;

public class MeepMeepTesting {
    public static TrajectoryVelocityConstraint FAST_VEL = new MinVelocityConstraint(Arrays.asList(
            new AngularVelocityConstraint(Math.toRadians(250)),
            new MecanumVelocityConstraint(70, 12.6)));

    public static TrajectoryVelocityConstraint NORMAL_VEL = new MinVelocityConstraint(Arrays.asList(
            new AngularVelocityConstraint(Math.toRadians(180)),
            new MecanumVelocityConstraint(40, 12.6)));

    public static TrajectoryVelocityConstraint SLOW_VEL = new MinVelocityConstraint(Arrays.asList(
            new AngularVelocityConstraint(Math.toRadians(150)),
            new MecanumVelocityConstraint(20, 12.6)));

    public static TrajectoryAccelerationConstraint FAST_ACCEL =  new ProfileAccelerationConstraint(70);
    public static TrajectoryAccelerationConstraint NORMAL_ACCEL =  new ProfileAccelerationConstraint(40);
    public static TrajectoryAccelerationConstraint SLOW_ACCEL =  new ProfileAccelerationConstraint(20);

    public static void main(String[] args) {


        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                .setConstraints(60, 60, Math.toRadians(225), Math.toRadians(225), 16.7)
                .setDimensions(14.3,14.3)

                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-24,64,Math.toRadians(90)))
                        .setTangent(Math.toRadians(-90))
                        .setConstraints(FAST_VEL, FAST_ACCEL)

                        //PRELOAD SCORE
//                        .addTemporalMarker(() -> {
//                            new AutoSpecOuttake().schedule();
//                        })

                        .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(-90))
//                        .addTemporalMarker(() -> {
//                            new WaitCommand(1000).schedule();
//                            new AutoSpecOuttake2().schedule();
//                        })
                        .waitSeconds(1)

//
                        .setTangent(90)
//                        .addTemporalMarker(() -> {
//                            new WaitCommand(1000);
//                            new ResetCommand().schedule();
//                        })

//
//
//
                        .splineToSplineHeading(new Pose2d(-35,26,Math.toRadians(-90)), Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-35, 16), Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-47, 15), Math.toRadians(90))
                        .setConstraints(NORMAL_VEL,NORMAL_ACCEL)

                        .splineToConstantHeading(new Vector2d(-47, 55), Math.toRadians(90))
                        //PUSHED FIRST SAMPLE TO OBSERVATION ZONE
                        .splineToConstantHeading(new Vector2d(-47, 16), Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-58, 16), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(-58, 55), Math.toRadians(90))
                        //PUSHED SECOND SAMPLE TO OBSERVATION ZONE
                        .splineToConstantHeading(new Vector2d(-58, 16), Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-65, 16), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(-65, 55), Math.toRadians(90))
                        .setConstraints(FAST_VEL,FAST_ACCEL)
                        //PUSHED THIRD SAMPLE TO OBSERVATION ZONE
                        //FIRST SAMPLE INTAKE
//                        .addTemporalMarker(() -> {
//                            new SpecIntakeCommand().schedule();
//                        })
                        .splineToConstantHeading(new Vector2d(-48, 55), Math.toRadians(90))
//                        .addTemporalMarker(() -> {
//                            new OuttakeClawCloseCommand().schedule();
//                        })
                        .waitSeconds(2.0)

                        .setTangent(-45)
                        //FIRST SAMPLE SCORE
//                        .addTemporalMarker(() -> {
//                            new WaitCommand(500).schedule();
//                            new AutoSpecOuttake().schedule();
//                        })

                        .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(-90))
//                        .addTemporalMarker(() -> {
//                            new WaitCommand(1000).schedule();
//                            new AutoSpecOuttake2().schedule();
//                        })

                        //SECOND SAMPLE INTAKE
                        .waitSeconds(1)
//                        .addTemporalMarker(() -> {
//                            new WaitCommand(500).schedule();
//                            new SpecIntakeCommand().schedule();
//                        })
                        .setTangent(135)
                        .splineToSplineHeading(new Pose2d(-48,55,Math.toRadians(-90)), Math.toRadians(90))

//                        .addTemporalMarker(() -> {
//                            new OuttakeClawCloseCommand().schedule();
//                        })
                        .waitSeconds(2.0)

                        .setTangent(-45)
                        //SECOND SAMPLE SCORE
//                        .addTemporalMarker(() -> {
//                            new WaitCommand(500).schedule();
//                            new AutoSpecOuttake().schedule();
//                        })

                        .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(-90))
//                        .addTemporalMarker(() -> {
//                            new WaitCommand(1000).schedule();
//                            new AutoSpecOuttake2().schedule();
//                        })

                        //THIRD SAMPLE INTAKE
                        .waitSeconds(1)
//                        .addTemporalMarker(() -> {
//                            new WaitCommand(500).schedule();
//                            new SpecIntakeCommand().schedule();
//                        })
                        .setTangent(135)
                        .splineToSplineHeading(new Pose2d(-48,55,Math.toRadians(-90)), Math.toRadians(90))
//                        .addTemporalMarker(() -> {
//                            new OuttakeClawCloseCommand().schedule();
//                        })
                        .waitSeconds(2.0)

                        .setTangent(-45)
                        //THIRD SAMPLE SCORE
//                        .addTemporalMarker(() -> {
//                            new WaitCommand(500).schedule();
//                            new AutoSpecOuttake().schedule();
//                        })

                        .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(-90))
//                        .addTemporalMarker(() -> {
//                            new WaitCommand(1000).schedule();
//                            new AutoSpecOuttake2().schedule();
//                        })
                        .waitSeconds(1)
//                        .addTemporalMarker(() -> {
//                            new WaitCommand(500).schedule();
//                            new ResetCommand().schedule();
//                        })
                        .setTangent(135)
                        .strafeTo(new Vector2d(-48,60))


                        .build());

//                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}