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

                .setConstraints(80, 80, 5/4.0*Math.PI, 5/4.0*Math.PI, 24)
                .setStartPose(new Pose2d(0, 64, Math.PI/2))

                .followTrajectorySequence(drive -> {
                    var builder = drive.trajectorySequenceBuilder(new Pose2d(0,64,Math.PI/2));
                    builder.setTangent(-Math.PI/2)
//                        .setConstraints(NORMAL_VEL, NORMAL_ACCEL)
//                        .addTemporalMarker(() -> {
//                            new HighSpecCommand().schedule();
//                        })

                        .waitSeconds(0.25)
                        .setTangent(-Math.PI/2)
                        .splineToConstantHeading(new Vector2d(6, 28), -Math.PI/2)

//                        .addTemporalMarker(() -> {
//                            new SequentialCommandGroup(
//                                    new WaitCommand(200),
//                                    new OuttakeClawOpenCommand()
//                            ).schedule();
//                        })

                        .waitSeconds(0.5)
                        .setTangent(Math.PI/2)

//                        .setConstraints(SLOW_VEL,SLOW_ACCEL)

//                        .addTemporalMarker(()->{
//                            new SequentialCommandGroup(
//                                    new WaitCommand(1000),
//                                    new ResetCommand()
//                            ).schedule();
//                        })

                        .splineToConstantHeading(new Vector2d(1, 40), Math.toRadians(180))
                        .splineToConstantHeading(new Vector2d(-33, 37), Math.toRadians(180))

                        .splineToConstantHeading(new Vector2d(-40, 16), Math.toRadians(180))
                        .splineToConstantHeading(new Vector2d(-47, 16), Math.toRadians(90))
//                        .setTangent(Math.PI/2)
                        .splineToConstantHeading(new Vector2d(-47, 51), Math.toRadians(90))

                        //JUST FINISHED PUSHING SAMPLE 1
                        .splineToConstantHeading(new Vector2d(-47, 16), Math.toRadians(225))
//                        .setTangent(Math.PI)
                        .splineToConstantHeading(new Vector2d(-56, 16), Math.toRadians(90))
//                        .setTangent(Math.PI/2)
                        .splineToConstantHeading(new Vector2d(-56, 51), Math.toRadians(90))

                        //JUST FINISHED PUSHING SAMPLE 2
                        .splineToConstantHeading(new Vector2d(-56, 16), Math.toRadians(225))
//                        .setTangent(Math.PI)
                        .splineToConstantHeading(new Vector2d(-63.5, 16), Math.toRadians(90))

//                        .setTangent(Math.PI/2)
                        .splineToConstantHeading(new Vector2d(-63.5, 51), Math.toRadians(90));

                            //SPECIMEN 1
//                        .addTemporalMarker(() -> {
//                            new SpecIntakeCommand().schedule();
//                        }

                    for (int i = 0; i < 4; i++) {

                                // IF I>0:
//                                .addTemporalMarker(()-> {
//                            new SequentialCommandGroup(
//                                    new OuttakeClawOpenCommand(),
//                                    new WaitCommand(500),
//                                    new SpecIntakeCommand()
//                            ).schedule();
//                        })
                                //-----
                                if(i>0){
                                    builder
                                            .setTangent(Math.PI/2)
                                            .splineToConstantHeading(new Vector2d(4-2*i, 35), Math.toRadians(90));
                                }
                                builder.splineToConstantHeading(new Vector2d(-48, 54), (i == 0 ? 0 : Math.PI/2))
                                .splineToConstantHeading(new Vector2d(-48, 63.5 - (i > 1 ? 0.7 : 0)), Math.PI/2)
                                .waitSeconds(1)
                                .setTangent(-Math.PI/4)
    //                        .addTemporalMarker(() -> {
    //                            new SequentialCommandGroup(
    //                                    new OuttakeClawCloseCommand(),
    //                                    new WaitCommand(300),
    //                                    new HighSpecCommand()
    //                            ).schedule();
    //                        })
    //                        .setConstraints(NORMAL_VEL, NORMAL_ACCEL)

                                .splineToConstantHeading(new Vector2d(4-2*i, 28), Math.toRadians(-90))

//                        .addTemporalMarker(()->{
//                            new ClawWristScoringSpecFlickCommand().schedule();
//                        })
                                .waitSeconds(0.3);
                    }

//                      .addTemporalMarker(()-> {
//                            new SequentialCommandGroup(
//                                    new OuttakeClawOpenCommand(),
//                                    new WaitCommand(1000),
//                                    new ResetCommand()
//                            ).schedule();
//                        });
                    builder
                        .setTangent(Math.PI/2)
                            .splineToLinearHeading(new Pose2d(-40, 56.3, Math.toRadians(-90)), Math.toRadians(90));
                        return builder.build();
                });


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

    }
