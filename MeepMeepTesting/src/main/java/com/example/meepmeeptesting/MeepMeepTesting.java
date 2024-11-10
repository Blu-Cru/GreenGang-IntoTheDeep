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

                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 16.5)

                // extendo is 10 inches

                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-24, 62, Math.toRadians(-90)))
                        //.setTangent(-90)
                        .splineToLinearHeading(new Pose2d(0, 42, Math.toRadians(90)), Math.toRadians(-90)) // turn towards bucket
                        .waitSeconds(2) // outtake block thats placed in auto

                        .splineToLinearHeading(new Pose2d(-53, 15, Math.toRadians(-90)), Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-53, 50, Math.toRadians(-90)), Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-45, 60, Math.toRadians(-90)), Math.toRadians(90))
                        .waitSeconds(2)

                        .splineToLinearHeading(new Pose2d(0, 42, Math.toRadians(-90)), Math.toRadians(0))
                        .waitSeconds(2)

                        .splineToLinearHeading(new Pose2d(-60, 15, Math.toRadians(-90)), Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-60, 50, Math.toRadians(-90)), Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-48, 62, Math.toRadians(-90)), Math.toRadians(90))






                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}