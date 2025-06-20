package org.firstinspires.ftc.teamcode.greengang.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;


@Config
@TeleOp(name = "servo test", group = "test")
public class ServoTest extends LinearOpMode {
    public static double position = 0.3;
    public static String name = "arm left";
    @Override
    public void runOpMode() throws InterruptedException {
        Servo test = hardwareMap.get(Servo.class, name);
        ServoControllerEx controller = (ServoControllerEx) test.getController();
        controller.setServoPwmDisable(test.getPortNumber());
        waitForStart();

        while(opModeIsActive()) {
            if(gamepad1.a) { //X button
                controller.setServoPwmEnable(test.getPortNumber());
                test.setPosition(position);
            } else {
                controller.setServoPwmDisable(test.getPortNumber());
            }

            telemetry.addData("name", name);
            telemetry.addData("position", test.getPosition());
            telemetry.update();
        }
    }
}