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
                .setStartPose(new Pose2d(39.6, 65, Math.toRadians(180)))

                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(39.6,65,Math.toRadians(-90))).setTangent(Math.toRadians(-90))

                .setTangent(Math.toRadians(-90))

                .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(0)) // 57, 55 before
                .waitSeconds(0.2)//original 1
                .waitSeconds(.2)
//                SAMPLE 1
                .splineToLinearHeading(new Pose2d(48, 45, Math.toRadians(-90)), Math.toRadians(180)) //47 b4

                .waitSeconds(3)//3.5
                .waitSeconds(.75)//prev .75

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before

                .waitSeconds(.2)

                //   SAMPLE 2
                .splineToLinearHeading(new Pose2d(58,45, Math.toRadians(-90)), Math.toRadians(180))

                .waitSeconds(3)//3.5

                .waitSeconds(0.75) //previous 0.75

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before
                .waitSeconds(1)



                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(60,48.6, Math.toRadians(-70)), Math.toRadians(225)) //old: 55,39.6,-70,225
                //made sample 3 intaking time longer
                .waitSeconds(3)//3.5

                .waitSeconds(.5)

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before

                .waitSeconds(.5)
                // PARK
                .splineToLinearHeading(new Pose2d(53,50, Math.toRadians(-90)), Math.toRadians(180))
                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

    }
