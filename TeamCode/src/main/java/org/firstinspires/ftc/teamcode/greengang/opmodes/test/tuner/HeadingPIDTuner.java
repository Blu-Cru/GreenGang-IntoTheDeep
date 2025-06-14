package org.firstinspires.ftc.teamcode.greengang.opmodes.test.tuner;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;

//TODO: CONFIRM THIS IS THE RIGHT WAY TO TUNE DT PID
@Config
@TeleOp(name = "heading PID Tuner", group = "test")
public class HeadingPIDTuner extends GreenLinearOpMode {
    public static double targetHeading;
    public static double targetY;
    public static double targetX;

    @Override
    public void initialize() {
        enableFTCDashboard();
        addDrivetrain();
        drivetrain.drivePower = 1;
    }

    @Override
    public void periodic() {
        double x = gamepad1.left_stick_x;
        double y= -gamepad1.left_stick_y;
        x = drivetrain.pid.xController.calculate(x);
        y = drivetrain.pid.yController.calculate(y);

        if(gamepad1.right_trigger > 0.2) {
            if(gamepad1.a) {
                drivetrain.driveToHeading(drivetrain.getPoseEstimate().getX(),drivetrain.getPoseEstimate().getY(),targetHeading);
            }
        } else {
            drivetrain.drive(0,0,0);
        }

    }

    @Override
    public void telemetry() {
        telemetry.addData("target heading ", targetHeading);
    }
}
