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

                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 16.5)

                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(24,60,0))

                // PRELOAD

                        .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180)) // 225, 180 before


                        // SAMPLE 1
                        .splineToLinearHeading(new Pose2d(47, 45, Math.toRadians(-90)), Math.toRadians(180))


                        .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180))


//                        // SAMPLE 2
                        .splineToLinearHeading(new Pose2d(58,45, Math.toRadians(-90)), Math.toRadians(180))


                        .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180))

//
//                        // SAMPLE 3
                        .splineToLinearHeading(new Pose2d(56,42, Math.toRadians(135-180)), Math.toRadians(225))



                        .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180))

//
//                        // PARK
                        .splineToLinearHeading(new Pose2d(48,58, Math.toRadians(90)), Math.toRadians(45))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}