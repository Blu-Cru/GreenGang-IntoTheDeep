package org.firstinspires.ftc.teamcode.subsystems.slides;

//import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

@Config

public class VertSlides implements Subsystem {
    DcMotor motorLeft, motorRight;
    private final PIDController pid;
    public double targetHeight;
    public static double vsP = 0.003, vsI = 0, vsD = 0.0001;
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
        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeft.setPower(0); // check what power it should be
        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setPower(0); // check what power it should be
        motorRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        state = STATE.INIT;
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    public VertSlides(HardwareMap hardwareMap) {
        motorLeft = hardwareMap.get(DcMotor.class, "slidesMotorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "slidesMotorRight");
        pid = new PIDController(vsP, vsI, vsD);
        state = STATE.INIT;
        targetHeight = 0;
        motorPower = 0;
    }

    public void update() {
        double vsCurrPosLeft = this.getVScurrLeftPos();
        motorPower = Range.clip(pid.calculate(vsCurrPosLeft), -0.6, 0.75);

        /*double vsRotateError = this.getVScurrPos() - pid.getSetPoint();
        if(Math.abs(vsRotateError) < 10 && this.getVScurrPos() < 15) {
            stopVSrotate();
        } else {*/
        setVSrotatePow(motorPower);
    }

    public void stopVSrotate() {
//        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        motorLeft.setPower(0);

        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setPower(0);
    }
    public double getVScurrLeftPos() {
        return motorLeft.getCurrentPosition();
    }

    public double getVScurrRightPos() {
        return motorRight.getCurrentPosition();
    }


    /*public void autoVSrotate(double power, int targetPos) {
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorPower = power;
        motor.setPower(power);
        motor.setTargetPosition(targetPos);
    }*/

    public void pidTo(double targetPos) {
        pid.setSetPoint(targetPos);
    }

    public void setVSrotatePow(double power) {
        motorPower = power;
//        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        motorLeft.setPower(power);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setPower(power);
    }
    public void updatePID() {
        pid.setPID(vsP, vsI, vsD);
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
        telemetry.addData("VS MotorLeft, MotorRight POW ", motorLeft.getPower() + ", " +motorRight.getPower());
        telemetry.addData("VS LEFT, RIGHT POS ", this.getVScurrLeftPos() + ", " + getVScurrRightPos());
        return null;
    }
}
