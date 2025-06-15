package org.firstinspires.ftc.teamcode.greengang.opmodes.auto.path;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(group = "paths")
public class BluSpecPath extends GreenLinearOpMode {
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
    Pose2d startPose;

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

        startPose = new Pose2d(0, 64, Math.PI/2);
        drivetrain.ppl.setPoseEstimate(startPose);
        drivetrain.setPoseEstimate(startPose);

        farBlue = drivetrain.trajectorySequenceBuilder(startPose)

                .setConstraints(SLOW_VEL, SLOW_ACCEL)

                .setTangent(-Math.PI/2)

                .splineToConstantHeading(new Vector2d(-5, 36.5 - 3), -Math.PI/2)

                .waitSeconds(0.5)
                .setTangent(Math.PI/2)

                .splineToConstantHeading(new Vector2d(-33, 37), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-40, 17), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-47, 17), Math.toRadians(180))
                .setTangent(Math.PI/2)
                .splineToConstantHeading(new Vector2d(-47, 51), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-47, 17), Math.toRadians(90))
                .setTangent(Math.PI)
                .splineToConstantHeading(new Vector2d(-58, 17), Math.toRadians(90))

                .splineToConstantHeading(new Vector2d(-58, 57.3), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-58, 17), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-62, 17), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-62, 57.3), Math.toRadians(90))
                .waitSeconds(1.1)
                .setTangent(-Math.PI/4)

                .splineToConstantHeading(new Vector2d(-6, 38 - 3), Math.toRadians(-90))
                .waitSeconds(0.5)
                .setTangent(3/4.0*Math.PI)

                .splineToConstantHeading(new Vector2d(-48, 56), Math.toRadians(90))

                .splineToConstantHeading(new Vector2d(-48, 60.3), Math.toRadians(90))
                .waitSeconds(1.1)

                .setTangent(-Math.PI/4)
                .splineToConstantHeading(new Vector2d(-7, 38.5 - 3), Math.toRadians(-90))
                .waitSeconds(0.3)

                .setTangent(3/4.0*Math.PI)

                .splineToConstantHeading(new Vector2d(-48, 56), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-48, 62.5), Math.toRadians(90))
                .waitSeconds(1.1)

                .setTangent(-Math.PI/4)
                .splineToConstantHeading(new Vector2d(-8, 40 - 3), Math.toRadians(-90))
                .waitSeconds(0.3)

                .setTangent(3/4.0*Math.PI)
                .splineToConstantHeading(new Vector2d(-52, 60), Math.toRadians(90))
                .build();
    }

    @Override
    public void initLoop() {
        outtakeClaw.close();
    }

    @Override
    public void periodic() {

        if (!drivetrain.isBusy() && !done){
            drivetrain.setPoseEstimate(startPose);
            drivetrain.ppl.setPoseEstimate(startPose);
            drivetrain.followTrajectorySequenceAsync(farBlue);
            done = true;
        }
        try {
            drivetrain.updateTrajectory();
        } catch (Exception e){
//            requestOpModeStop();
        }
    }

    public void telemetry(Telemetry tele){
        tele.addData("pose ", drivetrain.getPoseEstimate());
        tele.addData("ppl pose ", drivetrain.ppl.getPoseEstimate());
    }

}
