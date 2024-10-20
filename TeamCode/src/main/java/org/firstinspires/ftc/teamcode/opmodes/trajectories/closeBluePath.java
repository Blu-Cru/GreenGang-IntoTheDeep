package org.firstinspires.ftc.teamcode.opmodes.trajectories;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "close blue auto", group = "paths")
public class closeBluePath extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(24, 64, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        TrajectorySequence closeBlue = drive.trajectorySequenceBuilder(startPose)

                .splineToLinearHeading(new Pose2d(47, 50, Math.toRadians(90)), Math.toRadians(145)) // get first piece, 135 p good
                .waitSeconds(2) // collect piece 1
                .splineToLinearHeading(new Pose2d(50, 50, Math.toRadians(225)), Math.toRadians(180)) // turn towards bucket
                .waitSeconds(2) // outtake piece 1

                .splineToLinearHeading(new Pose2d(58,50, Math.toRadians(90)), Math.toRadians(180)) // get piece 2
                .waitSeconds(2) // collect piece 2
                .splineToLinearHeading(new Pose2d(50,50, Math.toRadians(225)), Math.toRadians(180)) // drive to bucket
                .waitSeconds(2) // outtake piece 2

                .splineToLinearHeading(new Pose2d(56,42, Math.toRadians(-225)), Math.toRadians(180)) // get piece 3
                .waitSeconds(2) // collect piece 3
                .splineToLinearHeading(new Pose2d(50,50, Math.toRadians(225)), Math.toRadians(180)) // drive to bucket
                .waitSeconds(2) // outtake piece 3

                .splineToLinearHeading(new Pose2d(48,62, Math.toRadians(90)), Math.toRadians(45)) // park 90 works
                .waitSeconds(2)
                .build();

        waitForStart();

        while(opModeIsActive()) {
            drive.followTrajectorySequenceAsync(closeBlue);
            drive.updateTrajectory();
        }
    }
}
