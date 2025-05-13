package org.firstinspires.ftc.teamcode.common.pathbase;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.common.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

public class BlueSampleTrajetories implements SamplePath{
    SampleMecanumDrive mecDrive;
    Pose2d startPose = new Pose2d(39.6, 65, Math.toRadians(180));
    Pose2d highBucket = new Pose2d(53, 55, Math.toRadians(225));
    Pose2d retractPose = new Pose2d(-54, -54, Math.toRadians(-225));
    Pose2d parkPose = new Pose2d(-48, -58, Math.toRadians(-90));

    Pose2d[] positions = {
            new Pose2d(48.8, 45, Math.toRadians(-90)),
            new Pose2d(57.8, 45, Math.toRadians(-90)),
            new Pose2d(63, 42, Math.toRadians(-60))
    };
    // Start -> High bucket (Preload Score)
    public TrajectorySequence toHighBucketPath;

    // High bucket -> Retract
    public TrajectorySequence retractFromHighBucket;

    public BlueSampleTrajetories(SampleMecanumDrive sampleMecanumDrive) {
        this.mecDrive = sampleMecanumDrive;
        toHighBucketPath = mecDrive.trajectorySequenceBuilder(startPose)
                .setTangent(-90)
                .splineToLinearHeading(highBucket, Math.toRadians(-180))
                .build();
        retractFromHighBucket = mecDrive.trajectorySequenceBuilder(toHighBucketPath.end())
                .splineToLinearHeading(retractPose, Math.toRadians(-180))
                .build();
    }

    @Override
    public Pose2d getStartPose() {
        return startPose;
    }

    @Override
    public TrajectorySequence getToHighBucket() {
        return toHighBucketPath;
    }

    @Override
    public TrajectorySequence getRetractFromHighBucket() {
        return retractFromHighBucket;
    }

    // Retract -> Sample
    @Override
    public Pose2d getSamplePose(int i) {
        return positions[i];
    }

    public TrajectorySequence getToSample(Pose2d fromPose, int i) {
        return mecDrive.trajectorySequenceBuilder(fromPose)
                .splineToLinearHeading(getSamplePose(i), Math.toRadians(-180))
                .build();
    }

    @Override
    public TrajectorySequence getToPark() {
        return toPark;
    }

    // Final park
   public  TrajectorySequence toPark = mecDrive.trajectorySequenceBuilder(highBucket)
            .splineToLinearHeading(parkPose, Math.toRadians(-45))
            .build();

}
