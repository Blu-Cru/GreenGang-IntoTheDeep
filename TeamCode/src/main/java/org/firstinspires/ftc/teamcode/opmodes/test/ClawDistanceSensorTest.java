package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;


@TeleOp(name = "color sensor test", group = "test")
public class ClawDistanceSensorTest extends GreenLinearOpMode {
    @Override
    public void periodic() {
//        ClawDistanceSensor.
    }

    @Override
    public void initialize() {
        addIntakeColorSensor();
    }

    public void telemetry() {
        intakeColorSensor.telemetry(telemetry);
    }

}