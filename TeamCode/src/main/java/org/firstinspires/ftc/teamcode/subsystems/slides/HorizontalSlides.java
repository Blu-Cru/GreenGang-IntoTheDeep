package org.firstinspires.ftc.teamcode.subsystems.slides;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HorizontalSlides {
    public static double kp = 0.0, ki = 0.0, kd = 0.0;
    enum STATE {
        MANUAL,
        IDLE,
        PID;
    }

    STATE state;
    DcMotorEx motor;
    PIDController pid;
    double position, velocity;
    double manualPower;

    public HorizontalSlides(HardwareMap HardwareMap){
        motor = HardwareMap.get(DcMotorEx.class, "horiz slides");
        pid = new PIDController(kp, ki, kd);
    }


    public void read(){
        position = motor.getCurrentPosition();
        velocity = motor.getVelocity();
    }

    public void write(){
        switch(state) {
            case PID:
                motor.setPower(pid.calculate(position));
                break;
            case IDLE:
                setPower(0.0);
                break;
            case MANUAL:
                motor.setPower(manualPower);
                manualPower = 0.0;
                break;


        }
    }

    public void pidTO(double ticks){
        state = STATE.PID;
        pid.setSetPoint(ticks);
    }

    public void setPower(double power){
        motor.setPower(power);
    }
    public void setManualPower(double power){
        state = STATE.MANUAL;
        manualPower = power;
    }
}
