package org.firstinspires.ftc.teamcode.subsystems.slides;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;

@Config
public class HorizontalSlides implements GreenSubsystem, Subsystem {
    public static double hsP = 0.003, hsI = 0.0004, hsD = 0.00005;
    int minpos = 0;
    int maxpos = 575;
    enum STATE {
        IDLE,
        PID,
        MANUAL
    }

    public enum LOC {
        RETRACTED,
        EXTENDED
    }

    public static double
            instlower = 1300, // TODO: to be determined
            instHalfway = 500,
            retract = 0,
            maxPower = 0.0;

    public STATE state;
    public LOC loc;
    DcMotorEx motor;
    PIDController pid;
    double position, velocity;

    public HorizontalSlides(HardwareMap hardwareMap){
        motor = hardwareMap.get(DcMotorEx.class, "hs motor");
        pid = new PIDController(hsP, hsI, hsD);
        state = STATE.IDLE;
    }

    @Override
    public void init() {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setPower(0);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pidTo(0);
        loc = LOC.RETRACTED;
    }

    public void pidTo(double ticks){
        maxPower=0.6;
        state = STATE.PID;
        loc = LOC.EXTENDED;
        pid.setSetPoint(Range.clip(ticks, minpos, maxpos));
    }
    public void updatePID(){
        pid.setPID(hsP, hsI, hsD);
    }

    public void autoLower() {

        state = STATE.PID;
        loc = LOC.EXTENDED;
        pidTo(instlower);
        maxPower = 0.6;

    }
    public void extendHalfway() {
        state = STATE.PID;
        loc = LOC.EXTENDED;
        pidTo(instHalfway);

    }


    public void retract() {
        state  = STATE.PID;
        loc = LOC.RETRACTED;
        pidTo(retract);
    }

    public void stop () {
        state = STATE.IDLE;
        loc = LOC.EXTENDED;
        motor.setPower(0);
    }

    public void manualSlide(double input) {
        state = STATE.MANUAL;
        loc = LOC.EXTENDED;
        pidTo(Range.clip(position + 300.0 * input, minpos,maxpos));
    }

    @Override
    public void telemetry(Telemetry tele){
        tele.addData("HS State", state);
        tele.addData("HS State (extend/retract) ", loc);
        tele.addData("HS PID Setpoint", pid.getSetPoint());
        tele.addData("Pos ", position);
    }

    public void update() {
        position = motor.getCurrentPosition();
        velocity = motor.getVelocity();
        switch (state) {
            case IDLE:
                motor.setPower(0);
                break;
            case PID:
            case MANUAL:
                double power = Range.clip(pid.calculate(position), -maxPower, maxPower);
                motor.setPower(power);
                break;
        }
        if (position < 10) {
            loc = LOC.RETRACTED;
        } else {
            loc = LOC.EXTENDED;
        }
    }
}
