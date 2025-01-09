package org.firstinspires.ftc.teamcode.subsystems.slides;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

public class HorizontalSlides implements GreenSubsystem {
    public static double hsP = 0.0, hsI = 0.0, hsD = 0.0;
    enum STATE {
        MANUAL,
        IDLE,
        PID;
    }

    public static double
            instlower = 50, // TODO: to be determined
            retract = 0;

    STATE state;
    public DcMotorEx motor;
    PIDController pid;
    double position, velocity;
    double motorPower;

    public HorizontalSlides(HardwareMap hardwareMap){
        motor = hardwareMap.get(DcMotorEx.class, "horiz slides");
        pid = new PIDController(hsP, hsI, hsD);
        state = STATE.IDLE;
        motorPower = 0;
    }

    @Override
    public void init() {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setPower(0);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void read(){
        position = motor.getCurrentPosition();
        velocity = motor.getVelocity();
    }

    @Override
    public void write(){
    }

    public void pidTo(double ticks){
        state = STATE.PID;
        pid.setSetPoint(ticks);
    }

    public void setPower(double power){
        motor.setPower(power);
    }
    public void setMotorPower(double power){
        state = STATE.MANUAL;
        motorPower = power;
    }
    public void updatePID(){
        pid.setPID(hsP, hsI, hsD);
    }

    public void autoLower() {
        pidTo(instlower);
    }

    public void retract() {
        pidTo(retract);
    }

    public void stop () {
        motor.setPower(0);
    }

    @Override
    public void telemetry(Telemetry tele){
        tele.addData("Horizontal state: ", state);
        tele.addData("Vel", velocity);
        tele.addData("power ", motorPower);
    }

    @Override
    public void update() {
        double pos = motor.getCurrentPosition();
        motorPower = Range.clip(pid.calculate(pos), -0.6, 0.75);
        setHSrotatePow(motorPower);
    }

    public void setHSrotatePow(double power){
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(power);
    }
}
