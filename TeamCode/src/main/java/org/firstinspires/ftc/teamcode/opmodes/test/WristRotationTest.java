package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.outtake.wrist.WristRotationServo;

@Config
@TeleOp(name = "WristRotationTest", group = "test")
public class WristRotationTest extends LinearOpMode {

    public static double position = 0.3;
    public static String name = "claw wrist";

    private WristRotationServo wristRotServo;
    private double manualPower;

    private boolean lastA = false;
    private boolean lastB = false;

    @Override
    public void runOpMode() throws InterruptedException {
        wristRotServo = new WristRotationServo(hardwareMap);
        wristRotServo.init();

        waitForStart();

        while (opModeIsActive()) {

            // Edge detection for A -> flip
            if (gamepad1.a && !lastA) {
                wristRotServo.flip();
            }
            lastA = gamepad1.a;

            // Edge detection for B -> turn90
            if (gamepad1.b && !lastB) {
                wristRotServo.turn90();
            }
            lastB = gamepad1.b;

            // Manual override with joystick
            manualPower = -gamepad1.left_stick_y;
            if (Math.abs(manualPower) > 0.1) {
                wristRotServo.manualRotate(manualPower / 500);
            }

            // Update and telemetry
            wristRotServo.update();
            wristRotServo.telemetry(telemetry);
            telemetry.update();
        }
    }
}
