package org.firstinspires.ftc.teamcode.common.pathbase;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

public class blueSample extends GreenLinearOpMode {
    Pose2d startPose = new Pose2d(39.6, 65, Math.toRadians(180));
    Pose2d highBucket = new Pose2d(53, 55, Math.toRadians(225));
    Pose2d retractPose = new Pose2d(-54, -54, Math.toRadians(-225));
    Pose2d parkPose = new Pose2d(-48, -58, Math.toRadians(-90));

    double[] xOffsets = {48.8, 57.8, 63};
    double[] yOffsets = {45, 45, 42};
    double[] headings = {-90, -90, -60};

    // Start -> High bucket (Preload Score)
    TrajectorySequence toHighBucketPath = drivetrain.trajectorySequenceBuilder(startPose)
            .setTangent(-90)
            .splineToLinearHeading(highBucket, Math.toRadians(-180))
            .build();

    // High bucket -> Retract
    TrajectorySequence retractFromHighBucket = drivetrain.trajectorySequenceBuilder(toHighBucketPath.end())
            .splineToLinearHeading(retractPose, Math.toRadians(-180))
            .build();

    // Retract -> Sample
    Pose2d getSamplePose(int i) {
        return new Pose2d(xOffsets[i], yOffsets[i], Math.toRadians(headings[i]));
    }

    TrajectorySequence getToSample(Pose2d fromPose, int i) {
        return drivetrain.trajectorySequenceBuilder(fromPose)
                .splineToLinearHeading(getSamplePose(i), Math.toRadians(-180))
                .build();
    }

    // Final park
    TrajectorySequence toPark = drivetrain.trajectorySequenceBuilder(highBucket)
            .splineToLinearHeading(parkPose, Math.toRadians(-45))
            .build();
}
