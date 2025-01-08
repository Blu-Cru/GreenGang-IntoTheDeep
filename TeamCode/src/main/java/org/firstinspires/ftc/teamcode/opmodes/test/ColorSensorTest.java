package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.IntakeColorSensor;

@TeleOp(name = "color sensor test", group = "test")
public class ColorSensorTest extends GreenLinearOpMode {
    IntakeColorSensor color;

    @Override
    public void periodic() {
        if(gamepad2.left_bumper) {
            if(color.isReading()) {
                color.stopReading();
            } else {
                color.startReading();
            }
        }
    }

    @Override
    public void initialize() {
        addIntakeColorSensor();
    }

    public void telemetry() {
        color.telemetry(telemetry);
    }

}