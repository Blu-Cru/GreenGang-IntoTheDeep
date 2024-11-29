package org.firstinspires.ftc.teamcode.subsystems.slides;

//import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class VertSlides implements Subsystem {
    public static double kP = 0.0, kI = 0.0, kD = 0.0;
    DcMotor motor;
    PIDController pid;
    Servo transfer;

    public enum STATE {
        INIT,
        LOW,
        HIGH,
        SPEC;
    }

    public STATE state;
    public VertSlides(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "slidesMotor");
        pid = new PIDController(kP, kI, kD);
        state = STATE.INIT;
    }

    public void init() {
        state = STATE.INIT;
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

    private void setTargetPos(double targetPos) {
        pid.setSetPoint(targetPos);
    }

    public void low(){
        state = STATE.LOW;
        //sum
    }

    public void high(){
        state = STATE.HIGH;
        //sum
    }
    public void spec(){
        state = STATE.SPEC;
        // sum
    }

    public void drop(){
        state = STATE.INIT;
        //sum
    }

    private void SetPower(double power) {
        motor.setPower(power);
    }

    public String telemetry(Telemetry telemetry) {
        telemetry.addData("VS MOTOR POW ", motor.getPower());
        telemetry.addData("VS POS ", motor.getCurrentPosition());
        telemetry.addData("VS STATE ", state);
        return null;
    }
}
