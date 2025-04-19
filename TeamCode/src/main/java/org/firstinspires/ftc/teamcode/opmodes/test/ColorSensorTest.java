package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;

@TeleOp(name = "color sensor test", group = "test")
public class ColorSensorTest extends GreenLinearOpMode {
    @Override
    public void periodic() {
        if(gamepad2.left_bumper) {
            if(intakeColorSensor.isReading()) {
                intakeColorSensor.stopReading();
            } else {
                intakeColorSensor.startReading();
            }
        }
    }

    @Override
    public void initialize() {
        addIntakeColorSensor();
    }

    public void telemetry() {
        intakeColorSensor.telemetry(telemetry);
    }

}