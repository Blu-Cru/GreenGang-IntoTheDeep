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

                .setConstraints(60, 60, Math.toRadians(225), Math.toRadians(225), 16.7)

                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-24,64,Math.toRadians(-90)))
                        .setTangent(Math.toRadians(-90))
                        // PRELOAD PLACEMENT
//                        .splineToLinearHeading(new Pose2d(-5, 42, Math.toRadians(90)), Math.toRadians(-90))
////                .addTemporalMarker(() -> {
////                    new AutoSpecOuttake().schedule();
////                })
//
//                        // PRELOAD FROM HUMAN PLACEMENT
//                        .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(90))
////                .addTemporalMarker(() -> {
////                    new AutoSpecIntake().schedule();
////                })
//
//                        .splineToLinearHeading(new Pose2d(-3, 42, Math.toRadians(90)), Math.toRadians(-90))
////                .addTemporalMarker(() -> {
////                    new AutoSpecOuttake().schedule();
////                })

                        // SPEC 3 PLACEMENT
                        .splineToLinearHeading(new Pose2d(-48, 13, Math.toRadians(-90)), Math.toRadians(180))
                        .waitSeconds(1)
                        .setTangent(90)

                        // Give sample to human player
                        .strafeTo(new Vector2d(-48,55))

//                        .splineToLinearHeading(new Pose2d(-48, 55, Math.toRadians(-90)), Math.toRadians(90))
                        .setTangent(-90)
                        .waitSeconds(1)

//                .addTemporalMarker(() -> {
//                    new SamplePassThroughCommand().schedule();
//                })
                        // SPEC 4 PLACEMENT
                        .splineToLinearHeading(new Pose2d(-58, 13, Math.toRadians(-90)), Math.toRadians(180))
                        .waitSeconds(1)
                        .setTangent(90)

                        .strafeTo(new Vector2d(-58,55))
//                        .splineToLinearHeading(new Pose2d(-58, 55, Math.toRadians(-90)), Math.toRadians(90))
                        .setTangent(-90)
                        .waitSeconds(1)

//                .addTemporalMarker(() -> {
//                    new SamplePassThroughCommand().schedule();
//                })
                        // SPEC 5 PLACEMENT

                        .splineToLinearHeading(new Pose2d(-65, 13, Math.toRadians(-90)), Math.toRadians(180))
                        .waitSeconds(1)
                        .setTangent(90)
                        .splineToLinearHeading(new Pose2d(-65, 55, Math.toRadians(-90)), Math.toRadians(90))
                        .waitSeconds(1)

//                .addTemporalMarker(() -> {
//                    new SamplePassThroughCommand().schedule();
//                })

                        // intake sample from human player
//                .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
//                .addTemporalMarker(() -> {
//                    new AutoSpecIntake().schedule();
//                })

                        // outtake sample
//                .splineToLinearHeading(new Pose2d(-1, 42, Math.toRadians(90)), Math.toRadians(-90))
//                .addTemporalMarker(() -> {
//                    new AutoSpecOuttake().schedule();
//                })



//                .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
//                .addTemporalMarker(() -> {
//                    new AutoSpecIntake().schedule();
//                })

//                .splineToLinearHeading(new Pose2d(1, 42, Math.toRadians(90)), Math.toRadians(-90))
//                .addTemporalMarker(() -> {
//                    new AutoSpecOuttake().schedule();
//                })



//                .splineToLinearHeading(new Pose2d(-48, 60, Math.toRadians(-90)), Math.toRadians(0))
//                .addTemporalMarker(() -> {
//                    new AutoSpecIntake().schedule();
//                })

//                .splineToLinearHeading(new Pose2d(3, 42, Math.toRadians(90)), Math.toRadians(-90))
//                .addTemporalMarker(() -> {
//                    new AutoSpecOuttake().schedule();
//                })



                        .build());
//                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}