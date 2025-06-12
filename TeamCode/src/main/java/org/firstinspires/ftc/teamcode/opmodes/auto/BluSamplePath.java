package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(group = "paths")
public class BluSamplePath extends GreenLinearOpMode {
    public static TrajectoryVelocityConstraint FAST_VEL = SampleMecanumDrive.getVelocityConstraint(48, Math.toRadians(220), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint NORMAL_VEL = SampleMecanumDrive.getVelocityConstraint(41, Math.toRadians(180), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint SLOW_VEL = SampleMecanumDrive.getVelocityConstraint(25, Math.toRadians(150), DriveConstants.TRACK_WIDTH);

    public static TrajectoryAccelerationConstraint FAST_ACCEL = SampleMecanumDrive.getAccelerationConstraint(48);
    public static TrajectoryAccelerationConstraint NORMAL_ACCEL = SampleMecanumDrive.getAccelerationConstraint(40);
    public static TrajectoryAccelerationConstraint SLOW_ACCEL = SampleMecanumDrive.getAccelerationConstraint(30);

    public static TrajectoryVelocityConstraint[] velos = {SLOW_VEL, NORMAL_VEL, FAST_VEL};
    public static TrajectoryAccelerationConstraint[] accels = {SLOW_ACCEL, NORMAL_ACCEL, FAST_ACCEL};

    TrajectorySequence farBlue;
    boolean done;

    @Override
    public void initialize() {
        addDrivetrain();
        addIntake();
        addStickyG1();
        addClawArm();
        addOuttakeClaw();
        addHorizontalSlides();
        addIntakeWrist();
        addClawWrist();
        addVertSlides();
        addHang();
        addIntakeColorSensor();

        Pose2d startPose = new Pose2d(39.6, 65, Math.toRadians(180));
        drivetrain.setPoseEstimate(startPose);

        farBlue = drivetrain.trajectorySequenceBuilder(startPose)

                .setTangent(Math.toRadians(-90))

                .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(0)) // 57, 55 before
                .waitSeconds(0.2)//original 1
                .waitSeconds(.2)
//                SAMPLE 1
                .splineToLinearHeading(new Pose2d(48, 45, Math.toRadians(-90)), Math.toRadians(180)) //47 b4

                .waitSeconds(3)//3.5
                .waitSeconds(.75)//prev .75

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before

                .waitSeconds(.2)

                //   SAMPLE 2
                .splineToLinearHeading(new Pose2d(58,45, Math.toRadians(-90)), Math.toRadians(180))

                .waitSeconds(3)//3.5

                .waitSeconds(0.75) //previous 0.75

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before
                .waitSeconds(1)



                // SAMPLE 3
                .splineToLinearHeading(new Pose2d(60,48.6, Math.toRadians(-70)), Math.toRadians(225)) //old: 55,39.6,-70,225
                //made sample 3 intaking time longer
                .waitSeconds(3)//3.5

                .waitSeconds(.5)

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(55, 55, Math.toRadians(225)), Math.toRadians(0)) // 225, 180 before

                .waitSeconds(.5)
                // PARK
                .splineToLinearHeading(new Pose2d(53,50, Math.toRadians(-90)), Math.toRadians(180))
                .build();
    }

    @Override
    public void initLoop() {
        outtakeClaw.close();
    }

    @Override
    public void periodic() {


        if (!drivetrain.isBusy() && !done){
            drivetrain.followTrajectorySequenceAsync(farBlue);
            done = true;
        }
        try {
            drivetrain.updateTrajectory();
        } catch (Exception e){
//            requestOpModeStop();
        }
    }

}