package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.IntakeColorSensor;

@TeleOp(name = "color sensor test", group = "test")
public class ColorSensorTest extends GreenLinearOpMode {
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
        color.telemetry(telemetry);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        color = new IntakeColorSensor(hardwareMap);

        while(opModeInInit()) {
            initialize();
        }

        waitForStart();

        while(opModeIsActive()) {
            color.read();
            color.telemetry(telemetry);
            telemetry.update();
        }

    }
}