package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.teamcode.subsystems.outtake.wrist.WristRotationServo;
import org.firstinspires.ftc.teamcode.subsystems.util.SmoothServo;


@Config
@TeleOp(name = "WristRotationTest", group = "test")
public class WristRotationTest extends LinearOpMode {
    public static double position = 0.3;
    public static String name = "claw wrist";
    WristRotationServo wristRotservo = new WristRotationServo();
    double manualPower;
    @Override
    public void runOpMode() throws InterruptedException {
        wristRotservo.init();
        waitForStart();

        while(opModeIsActive()) {
            if(gamepad1.a) { //X button
                wristRotservo.flip();
            }
            manualPower = -gamepad1.left_stick_y;
            if(Math.abs(manualPower) > 0.1) {
                wristRotservo.manualRotate(manualPower);
            }

            wristRotservo.telemetry(telemetry);
            telemetry.update();
        }
    }
}
