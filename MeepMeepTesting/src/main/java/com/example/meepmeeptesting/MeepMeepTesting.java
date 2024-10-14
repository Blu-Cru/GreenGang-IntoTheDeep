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

                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width

                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                // extendo is 10 inches

                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(24, 58, Math.toRadians(90)))

                        .splineToLinearHeading(new Pose2d(47, 50, Math.toRadians(90)), Math.toRadians(180)) // get first piece
                        .waitSeconds(2) // collect piece 1
                        .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180)) // turn towards bucket
                        .waitSeconds(2) // outake piece 1

                        .splineToLinearHeading(new Pose2d(58,50, Math.toRadians(90)), Math.toRadians(180)) // get piece 2
                        .waitSeconds(2) // collect piece 2
                        .splineToLinearHeading(new Pose2d(50,50, Math.toRadians(225)), Math.toRadians(180)) // drive to bucket
                        .waitSeconds(2) // outake piece 2

                        .splineToLinearHeading(new Pose2d(56,42, Math.toRadians(-225)), Math.toRadians(180)) // get piece 3
                        .waitSeconds(2) // collect piece 3
                        .splineToLinearHeading(new Pose2d(50,50, Math.toRadians(225)), Math.toRadians(180)) // drive to bucket
                        .waitSeconds(2) // outtake piece 3

                        .splineToLinearHeading(new Pose2d(48,62, Math.toRadians(90)), Math.toRadians(180)) // park
                        .waitSeconds(2)
                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}