package org.firstinspires.ftc.teamcode.common.pathbase;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

public class redSample extends GreenLinearOpMode {
    Pose2d startPose = new Pose2d(-24, -64, Math.toRadians(-90));
    Pose2d toHighBucket = new Pose2d(-50, -50, Math.toRadians(-225));
    Pose2d retractPose = new Pose2d(-54, -54, Math.toRadians(-225));
    Pose2d parkPose = new Pose2d(-48, -58, Math.toRadians(-90));

    double[] xOffsets = {-53, -65, -56};
    double[] yOffsets = {-45, -45, -42};
    double[] headings = {90, 90, 45};


    // Start -> high bucket (Preload Score)
    TrajectorySequence highBucket = drivetrain.trajectorySequenceBuilder(startPose)
            .setTangent(-90)
            .splineToLinearHeading(toHighBucket, Math.toRadians(-180))
            .build();

    // high bucket -> Retract
    TrajectorySequence retractFromHighBucket = drivetrain.trajectorySequenceBuilder(highBucket.end())
            .splineToLinearHeading(retractPose, Math.toRadians(-180))
            .build();

    // Retract -> Sample (parameterized by index/offset)
    Pose2d getSamplePose(int i) {
        return new Pose2d(xOffsets[i], yOffsets[i], Math.toRadians(headings[i]));
    }

    TrajectorySequence getToSample(Pose2d fromPose, int i) {
        return drivetrain.trajectorySequenceBuilder(fromPose)
                .splineToLinearHeading(getSamplePose(i), Math.toRadians(-180))
                .build();
    }

    // Final park
    TrajectorySequence toPark = drivetrain.trajectorySequenceBuilder(toHighBucket)
            .splineToLinearHeading(parkPose, Math.toRadians(-45))
            .build();
}
