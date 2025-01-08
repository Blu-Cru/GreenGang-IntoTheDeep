package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
@TeleOp(name = "arm test", group = "test")
public class ArmTest extends GreenLinearOpMode {

    DcMotorEx armRotate;

    public static int position = 40;
    double power;

    @Override
    public void initialize() {
        addClawArm();
        armRotate = hardwareMap.get(DcMotorEx.class, "armRotate");
        armRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        armRotate.setPower(0);
        power = 0;
        armRotate.setDirection(DcMotorSimple.Direction.REVERSE);
        armRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRotate.setTargetPosition(0);
        armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void periodic(){
        power = -gamepad1.left_stick_y;
        armRotate.setPower(power / 2);
    }

    @Override
    public void telemetry(){
        telemetry.addData("Rotation power:", power);
        telemetry.addData("IntakeArm Position:", armRotate.getCurrentPosition());
    }

}
