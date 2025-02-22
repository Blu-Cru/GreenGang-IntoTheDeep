package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {

        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17)

                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(36,65,0))
//                        .setTangent(90)

                // PRELOAD

                        .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before
//                        .turn(Math.toRadians(-90))
//                                .waitSeconds(3)
//                                .waitSeconds(1)
//                                .waitSeconds(4)

//
//                        // SAMPLE 1
                        .splineToLinearHeading(new Pose2d(47, 40, Math.toRadians(-90)), Math.toRadians(180))
//                                .waitSeconds(1)

                        .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(180))
//                                .waitSeconds(3)
//                                .waitSeconds(5)
//
////                        // SAMPLE 2
                        .splineToLinearHeading(new Pose2d(58,40, Math.toRadians(-90)), Math.toRadians(180))
//                                .waitSeconds(1)

                        .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(180))
//                                .waitSeconds(3)
//                                .waitSeconds(5)
////
////                        // SAMPLE 3
                        .splineToLinearHeading(new Pose2d(56,38, Math.toRadians(135-180)), Math.toRadians(225))
//                                .waitSeconds(1)


                        .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(180))
//                                .waitSeconds(3)
//                                .waitSeconds(5)
////
////                        // PARK
//                        .splineToLinearHeading(new Pose2d(48,58, Math.toRadians(200)), Math.toRadians(45))
                        .build());
//                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}