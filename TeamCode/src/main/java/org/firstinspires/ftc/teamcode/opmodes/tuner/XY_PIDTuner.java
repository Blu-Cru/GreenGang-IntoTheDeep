package org.firstinspires.ftc.teamcode.opmodes.tuner;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;

@Config
@TeleOp(name = "XY PID Tuner", group = "test")
public class XY_PIDTuner extends GreenLinearOpMode {
    public static double targetX = 0;
    public static double targetY = 0;

    @Override
    public void initialize() {
        enableFTCDashboard();
        addDrivetrain();
        drivetrain.drivePower = 1;
    }

    @Override
    public void periodic() {
        // Use button to trigger movement
        if (gamepad1.right_trigger > 0.2 && gamepad1.a) {
            drivetrain.driveToHeading(targetX, targetY);
        } else {
            drivetrain.drive(0, 0, 0); // Stop movement
        }
    }

    @Override
    public void telemetry() {
        telemetry.addData("Target X", targetX);
        telemetry.addData("Target Y", targetY);
        telemetry.addData("Current X", drivetrain.getPoseEstimate().getX());
        telemetry.addData("Current Y", drivetrain.getPoseEstimate().getY());
    }
}