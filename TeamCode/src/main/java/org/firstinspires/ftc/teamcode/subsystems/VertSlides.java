package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class VertSlides {
    public static double kP = 0.0, kI = 0.0, kD = 0.0;
    DcMotor motor;
    PIDController pid;

    public VertSlides(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "slidesMotor");

        pid = new PIDController(kP, kI, kD);
    }

    public void init() {
        pid.setSetPoint(0);
    }

    public void update(){
        double currentPos = (double) motor.getCurrentPosition();
        double power = Range.clip(pid.calculate(currentPos), -0.8, 0.8);
        SetPower(power);
    }

    public void setTargetPos(double targetPos) {
        pid.setSetPoint(targetPos);
    }

    public void SetPower(double power) {
        motor.setPower(power);
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("motor power", motor.getPower());
        telemetry.addData("current pos", motor.getCurrentPosition());
    }
}
