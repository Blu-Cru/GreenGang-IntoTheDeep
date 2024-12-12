package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "vs test", group = "test")
public class VerticalSlidesTest extends LinearOpMode{
    public static String name = "leftFront";

    @Override
    public void runOpMode() throws InterruptedException{
        DcMotorEx motorL = hardwareMap.get(DcMotorEx.class, "slidesMotorRight");
        DcMotorEx motorR = hardwareMap.get(DcMotorEx.class, "slidesMotorLeft");
        motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double vert;
        motorL.setPower(0);

        motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorR.setPower(0);

        waitForStart();

        while(opModeIsActive()) {
            motorR = hardwareMap.get(DcMotorEx.class, "slidesMotorRight");
            motorL = hardwareMap.get(DcMotorEx.class, "slidesMotorLeft");

            vert = -gamepad1.left_stick_y;

            if(Math.abs(vert) > 0.1) {
                motorR.setPower(vert);
                motorL.setPower(vert);
            } else {
                motorR.setPower(vert);
                motorL.setPower(vert);
            }
//
//            telemetry.addData("name", name);
//            telemetry.addData("power", motorL.getPower());
//            telemetry.addData("current position", motorL.getCurrentPosition());
            telemetry.update();
        }
    }
}
