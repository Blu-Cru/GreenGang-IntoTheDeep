package org.firstinspires.ftc.teamcode.opmodes.tuner;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;

@Config
@TeleOp(group = "test")
public class DrivePIDTuner extends GreenLinearOpMode {
    public static double targetX = 0, targetY = 0, targetHeading = 0;

    @Override
    public void initialize() {
        addDrivetrain();
        enableFTCDashboard();
    }

    @Override
    public void onStart() {
        targetX = drivetrain.pose.getX();
        targetY = drivetrain.pose.getY();
        targetHeading = drivetrain.heading;
    }

    @Override
    public void periodic() {
        drivetrain.updatePID();

        if(gamepad1.a) {
            drivetrain.pidTo(new Pose2d(targetX, targetY, targetHeading));
        } else {
            drivetrain.idle();

            if(gamepad1.right_stick_button) {
                drivetrain.setHeading(Math.PI/2);
            }

            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double rot = -gamepad1.right_stick_x;

            Pose2d drivePose = new Pose2d(x, y, rot);

            drivetrain.fieldCentricDrive(drivePose);
        }

    }

    @Override
    public void telemetry() {
        telemetry.addData("Target heading", targetHeading);
        telemetry.addData("Target x", targetX);
        telemetry.addData("Target y", targetY);
        telemetry.addData("Current heading", drivetrain.heading);
        telemetry.addData("Current x", drivetrain.pose.getX());
        telemetry.addData("Current y", drivetrain.pose.getY());

    }
}