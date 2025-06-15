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
import java.util.Vector;


public class MeepMeepTesting {

    public static void main(String[] args) {

        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(13.964567, 15.43553)

                .setConstraints(80, 80, Math.toRadians(225), Math.toRadians(225), 24)
                .setStartPose(new Pose2d(0, 64, Math.toRadians(90)))

                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(0,64,Math.toRadians(90))).setTangent(Math.toRadians(-90))

                    .setTangent(Math.toRadians(-90))

                    .splineToConstantHeading(new Vector2d(-5, 36.5 - 3), Math.toRadians(-90))

                    .waitSeconds(0.5)
                    .setTangent(Math.toRadians(90))

                    .splineToConstantHeading(new Vector2d(-33, 37), Math.toRadians(180))
                    .splineToConstantHeading(new Vector2d(-40, 17), Math.toRadians(180))
                    .splineToConstantHeading(new Vector2d(-47, 17), Math.toRadians(180))
                        .setTangent(90)
                    .splineToConstantHeading(new Vector2d(-47, 51), Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-47, 17), Math.toRadians(90))
                        .setTangent(180)
                    .splineToConstantHeading(new Vector2d(-58, 17), Math.toRadians(90))

                    .splineToConstantHeading(new Vector2d(-58, 57.3), Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-58, 17), Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-62, 17), Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-62, 57.3), Math.toRadians(90))
                    .waitSeconds(1.1)
                    .setTangent(Math.toRadians(-45))

                    .splineToConstantHeading(new Vector2d(-6, 38 - 3), Math.toRadians(-90))
                    .waitSeconds(0.5)
                    .setTangent(Math.toRadians(135))

                    .splineToConstantHeading(new Vector2d(-48, 56), Math.toRadians(90))

                    .splineToConstantHeading(new Vector2d(-48, 60.3), Math.toRadians(90))
                    .waitSeconds(1.1)

                    .setTangent(Math.toRadians(-45))
                    .splineToConstantHeading(new Vector2d(-7, 38.5 - 3), Math.toRadians(-90))
                    .waitSeconds(0.3)

                    .setTangent(Math.toRadians(135))

                    .splineToConstantHeading(new Vector2d(-48, 56), Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-48, 62.5), Math.toRadians(90))
                    .waitSeconds(1.1)

                    .setTangent(Math.toRadians(-45))
                    .splineToConstantHeading(new Vector2d(-8, 40 - 3), Math.toRadians(-90))
                    .waitSeconds(0.3)

                    .setTangent(Math.toRadians(135))
                    .splineToConstantHeading(new Vector2d(-52, 60), Math.toRadians(90))
                    .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

    }
