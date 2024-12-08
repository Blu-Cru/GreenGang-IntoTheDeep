package org.firstinspires.ftc.teamcode.subsystems.slides;

//import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class VertSlides implements Subsystem {
    public static double kP = 0.0, kI = 0.0, kD = 0.0;
    DcMotor motor;
    private final PIDController pid;

    public double targetHeight;
    public static double armP = 0.003, armI = 0, armD = 0.0001;
    public double motorPower;

    public static int
            down = 0,
            highBucket = 0,
            lowBucket = 0,
            intkSpec = 0,
            highSpec = 0;


    public enum STATE {
        INIT,
        LOW,
        HIGH,
        OUTSPEC,
        INSPEC;
    }

    public STATE state;

    public void init() {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setPower(0); // check what power it should be
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        state = STATE.INIT;
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    public VertSlides(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "slidesMotor");
        pid = new PIDController(kP, kI, kD);
        state = STATE.INIT;
        targetHeight = 0;
        motorPower = 0;
    }

    public double getAngle(double armPos) {
        return armPos * 1/3895.9 * (2*Math.PI);
    }

    public void update() {
        double vsCurrPos = this.getVScurrPos();

        motorPower = Range.clip(pid.calculate(vsCurrPos), -0.6, 0.75);
        double armRotateError = this.getVScurrPos() - pid.getSetPoint();

        if(Math.abs(armRotateError) < 10 && this.getVScurrPos() < 15) {
            stopArmRotate();
        } else {
            setArmRotatePower(motorPower);
        }
    }

    public void stopArmRotate() {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(0);
    }
    public double getVScurrPos() {
        return getAngle(motor.getCurrentPosition());
    }

    public void autoArmRotate(double power, int targetPos) {
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorPower = power;
        motor.setPower(power);
        motor.setTargetPosition(targetPos);
    }

    public void pidTo(double targetPos) {
        pid.setSetPoint(targetPos);
    }

    public void setArmRotatePower(double power) {
        motorPower = power;
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(power);
    }
    public void updatePID() {
        pid.setPID(armP, armI, armD);
    }

    public void setTargetPos(double targetPos) {
        pid.setSetPoint(targetPos);
    }

    public void low(){
        state = STATE.LOW;
        targetHeight = down;
    }

    public void high(){
        state = STATE.HIGH;
        targetHeight = highBucket;
    }
    public void outkSpec(){
        state = STATE.OUTSPEC;
        targetHeight = highSpec;
    }

    public void intkSpec(){
        // sum
        state = STATE.INSPEC;
        targetHeight = intkSpec;
    }

    public void drop(){
        state = STATE.INIT;
        targetHeight = down;
    }

    public String telemetry(Telemetry telemetry) {
        telemetry.addData("VS MOTOR POW ", motor.getPower());
        telemetry.addData("VS POS ", motor.getCurrentPosition());
        return null;
    }
}
