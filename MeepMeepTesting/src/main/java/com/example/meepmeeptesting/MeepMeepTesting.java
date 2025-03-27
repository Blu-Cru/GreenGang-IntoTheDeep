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

                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(39.6, 65,Math.toRadians(90)))
                        .splineToLinearHeading(new Pose2d(20, 13, Math.toRadians(0)), Math.toRadians(180))

//
                        .build());

//                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}