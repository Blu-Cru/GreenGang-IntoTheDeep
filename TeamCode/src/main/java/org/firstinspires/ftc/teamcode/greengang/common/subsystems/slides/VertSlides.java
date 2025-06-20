package org.firstinspires.ftc.teamcode.greengang.common.subsystems.slides;

//import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.common.util.GreenSubsystem;

@Config

public class VertSlides implements GreenSubsystem, Subsystem {
    public DcMotor motorLeft, motorRight;
    private final PIDController pid;
    public double targetHeight;
    public static double vsP = 0.0088, vsI = 0.00004, vsD = 0.00001;
    private double motorPower;
    public boolean highspec;
    double lowBar=0;
    double highBar=0;

    public static int
            init = 0,
            highBucket = 2120,
            lowBucket = 800,
            ascent2 = 976,
            lowSpec = 465,
            highSpec = 580;


    public enum STATE {
        INIT,
        LOW,
        START,
        HIGH,
        HIGHSPEC,
        HIGHSPECSCORE,
        LOWSPEC,
        ASCENT2,
        MANUAL;
    }

    public STATE state;
    public enum TYPE {
        PID,
        IDLE;
    }

    public TYPE type;

    public void init() {
        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeft.setPower(0); // check what power it should be
        motorLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setPower(0); // check what power it should be
        motorRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pidTo(0);
        state = STATE.INIT;
        type = TYPE.IDLE;
    }

    public VertSlides(HardwareMap hardwareMap) {
        motorLeft = hardwareMap.get(DcMotor.class, "vs left");
        motorRight = hardwareMap.get(DcMotor.class, "vs right");
        pid = new PIDController(vsP, vsI, vsD);
        state = STATE.INIT;
        targetHeight = 0;
        motorPower = 0;
    }

    public void update() {
        switch (type) {
            case PID:
                double vsCurrPosLeft = this.getVScurrLeftPos();
                motorPower = Range.clip(pid.calculate(vsCurrPosLeft), -1, 1);
                setPow(motorPower);
                break;
            case IDLE:
                setPow(0);
                break;
        }

    }

    public void stopVSrotate() {
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        setPow(0);
    }

    public double getVScurrRightPos() {
        return -motorRight.getCurrentPosition();
    }
    public double getVScurrLeftPos() {
        return motorLeft.getCurrentPosition();
    }
    public void pidTo(double targetPos) {
        type = TYPE.PID;
        pid.setSetPoint(targetPos);
    }

    public void setPow(double power) {
        motorPower = Range.clip(power, -1,1);
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }
    public void updatePID() {
        pid.setPID(vsP, vsI, vsD);
    }

    public void manual(double num){
        state = STATE.MANUAL;
        setPow(num);
    }

    public void start(){
        state = STATE.START;
        targetHeight = 0;
        pidTo(targetHeight);
    }

    public void slightLift(){
        targetHeight = 350;
        pidTo(targetHeight);
    }

    public void highBucket(){
        state = STATE.HIGH;
        targetHeight = highBucket;
        pidTo(targetHeight);
    }
    public void highSpec(){
        state = STATE.HIGHSPEC;
        targetHeight = highSpec;
        pidTo(targetHeight);
        highspec =!highspec;
    }
    public void highSpecScore(){
        state = STATE.HIGHSPECSCORE;
        targetHeight = highSpec-40;
        pidTo(targetHeight);

    }

    public void lowBucket() {
        state = STATE.LOW;
        targetHeight = lowBucket;
        pidTo(targetHeight);
    }
    public void lowSpec() {
        state = STATE.LOWSPEC;
        targetHeight = lowSpec;
        pidTo(targetHeight);
        highspec =!highspec;
    }
    public void ascent2(){
        state = STATE.ASCENT2;
        targetHeight = ascent2;
        pidTo(targetHeight);
    }

    public void lowBar(){
        pidTo(lowBar);
    }

    public void highBar(){
        pidTo(highBar);
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("vert slides pos ", getVScurrRightPos());
        telemetry.addData("vert slides state ", state);
        telemetry.addData("vert slides curr power ", motorPower);
    }
}
