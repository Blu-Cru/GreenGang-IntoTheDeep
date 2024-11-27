package org.firstinspires.ftc.teamcode.subsystems.slides;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

@Config
public class VertSlides implements Subsystem {
    public static double kP = 0.0, kI = 0.0, kD = 0.0;
    DcMotor motor;
    PIDController pid;

    Servo transfer;

    public VertSlides(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "slidesMotor");
        pid = new PIDController(kP, kI, kD);
    }

    public void init() {
        pid.setSetPoint(0);
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    public void update(){
        double currentPos = (double) motor.getCurrentPosition();
        double power = Range.clip(pid.calculate(currentPos), -0.8, 0.8);
        SetPower(power);
    }

    public void setTargetPos(double targetPos) {
        pid.setSetPoint(targetPos);
    }

    public void transfer() {
        transfer.setPosition(0);
    }

    public void SetPower(double power) {
        motor.setPower(power);
    }

    public String telemetry(Telemetry telemetry) {
        telemetry.addData("motor power", motor.getPower());
        telemetry.addData("current pos", motor.getCurrentPosition());
        return null;
    }
}
