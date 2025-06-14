package org.firstinspires.ftc.teamcode.greengang.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;


@TeleOp(group = "test")
public class ClawDistanceSensorTest extends GreenLinearOpMode {
    @Override
    public void periodic() {

    }

    @Override
    public void initialize() {
        addClawDistanceSensor();
    }

    public void telemetry() {
        distanceSensor.telemetry(telemetry);
    }

}