package org.firstinspires.ftc.teamcode.subsystems.slides;

//import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

@Config

public class VertSlides implements GreenSubsystem, Subsystem {
    public DcMotor motorLeft, motorRight;
    private final PIDController pid;
    public double targetHeight;
    public static double vsP = 0.013, vsI = 0, vsD = 0.0001;
    public double motorPower;

    public static int
            init = 0,
            highBucket = 2890,
            lowBucket = 1636,
            highSpec = 1507,
            lowSpec = 800;

    public enum STATE {
        INIT,
        LOW,
        START,
        HIGH,
        HIGHSPEC,
        LOWSPEC,
        MANUAL;
    }

    public STATE state;

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
        double vsCurrPosLeft = this.getVScurrRightPos();
        motorPower = Range.clip(pid.calculate(vsCurrPosLeft), -0.6, 0.75);
        setVSrotatePow(motorPower);
    }

    public void stopVSrotate() {
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeft.setPower(0);

        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setPower(0);
    }
    public double getVScurrLeftPos() {
        return motorLeft.getCurrentPosition();
    }

    public double getVScurrRightPos() {
        return motorRight.getCurrentPosition();
    }
    public void pidTo(double targetPos) {
        pid.setSetPoint(targetPos);
    }

    public void setVSrotatePow(double power) {
        motorPower = power;
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeft.setPower(power);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setPower(power);
    }
    public void updatePID() {
        pid.setPID(vsP, vsI, vsD);
    }

    public void manual(double num){
        state = STATE.MANUAL;
        setVSrotatePow(num);
    }

    public void start(){
        state = STATE.START;
        targetHeight = init;
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
    }

    public String telemetry(Telemetry telemetry) {
        return null;
    }
}
