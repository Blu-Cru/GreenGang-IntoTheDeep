package org.firstinspires.ftc.teamcode.testing;


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@TeleOp(name="color sensor test", group="test")
public class ColorSensorTest extends LinearOpMode {
    ModernRoboticsI2cColorSensor sensor;

    @Override
    public void runOpMode() throws InterruptedException {
        sensor = hardwareMap.get(ModernRoboticsI2cColorSensor.class, "color");
        NormalizedRGBA colorRgba = sensor.getNormalizedColors();

        waitForStart();

        while (opModeIsActive()) {
            colorRgba = sensor.getNormalizedColors();
            telemetry.addData("rgba: ", colorRgba.red + ", " + colorRgba.green + ", " + colorRgba.blue);
            telemetry.update();
        }
    }
}
