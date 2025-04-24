package org.firstinspires.ftc.teamcode.common.pathbase;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

public interface SamplePath {
    Pose2d getStartPose();
    TrajectorySequence getToHighBucket();
    TrajectorySequence getRetractFromHighBucket();
    Pose2d getSamplePose(int i);
    TrajectorySequence getToSample(Pose2d fromPose, int i);
    TrajectorySequence getToPark();
}