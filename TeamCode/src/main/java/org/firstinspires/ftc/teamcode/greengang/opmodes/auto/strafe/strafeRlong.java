package org.firstinspires.ftc.teamcode.greengang.opmodes.auto.strafe;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "strafe right long", group = "paths")
public class strafeRlong extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(57, 64, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        TrajectorySequence strafeR = drive.trajectorySequenceBuilder(startPose)
                .splineToLinearHeading(new Pose2d(57, 67, Math.toRadians(90)), Math.toRadians(-90))
                .build();

        waitForStart();

        double startTime = System.currentTimeMillis();

        while(opModeIsActive()) {
            if(System.currentTimeMillis() - startTime < 10000) {
                drive.followTrajectorySequenceAsync(strafeR);
                drive.updateTrajectory();
            } else {
                drive.setWeightedDrivePower(new Pose2d(0,0,0));
            }
        }
    }
}