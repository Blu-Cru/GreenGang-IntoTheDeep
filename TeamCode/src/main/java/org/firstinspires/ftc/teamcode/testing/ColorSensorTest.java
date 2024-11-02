package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.IntakeColorSensor;

@TeleOp(name = "color sensor test", group = "test")
public class ColorSensorTest extends LinearOpMode {
    IntakeColorSensor color;

    public void periodic() {
        if(gamepad2.left_bumper) {
            if(color.isReading()) {
                color.stopReading();
            } else {
                color.startReading();
            }
        }
    }

    public void initialize() {
        color.startReading();
    }

    public void telemetry() {
        color.testTelemetry(telemetry);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        color = new IntakeColorSensor(hardwareMap);

        while(opModeInInit()) {
            initialize();
            telemetry.update();
        }

        waitForStart();

        while(opModeIsActive()) {
            periodic();

            color.read();
            color.testTelemetry(telemetry);
            telemetry.update();
        }

    }
}