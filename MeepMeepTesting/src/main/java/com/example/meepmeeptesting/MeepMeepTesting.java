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

                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-24, 64, Math.toRadians(-90)))

                        //.setTangent(-90)

                        // PRELOAD PLACEMENT
                        .splineToLinearHeading(new Pose2d(-5, 42, Math.toRadians(90)), Math.toRadians(-90))

                        // PRELOAD FROM HUMAN PLACEMENT
                        .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-3, 42, Math.toRadians(90)), Math.toRadians(-90))



                        // SPEC 3 PLACEMENT
                        .splineToLinearHeading(new Pose2d(-48, 45, Math.toRadians(-90)), Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-48, 50, Math.toRadians(-90)), Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(-1, 42, Math.toRadians(90)), Math.toRadians(-90))

                        // SPEC 4 PLACEMENT
                        .splineToLinearHeading(new Pose2d(-58, 45, Math.toRadians(-90)), Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-48, 50, Math.toRadians(-90)), Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(1, 42, Math.toRadians(90)), Math.toRadians(-90))

                        // SPEC 5 PLACEMENT
                        .splineToLinearHeading(new Pose2d(-56, 41, Math.toRadians(-135)), Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-48, 50, Math.toRadians(-90)), Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(3, 42, Math.toRadians(90)), Math.toRadians(-90))

                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}