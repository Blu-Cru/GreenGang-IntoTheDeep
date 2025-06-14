package org.firstinspires.ftc.teamcode.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;

public class ClawWrist implements GreenSubsystem, Subsystem {
    Servo clawWrist;
    public enum STATE {
        INIT,
        BUCKET,
        LOWOUTSPEC,
        HIGHOUTSPEC,
        INSPECTRANSFER,
        INSPEC,

    }
    double amt = 0.20;
    public STATE state;

    public ClawWrist(HardwareMap hardwareMap) {
        clawWrist =  hardwareMap.get(Servo.class, "outtake wrist");;
        state = STATE.INIT;
    }

    @Override
    public void init() {
        clawWrist.setPosition(.7-amt);
        state = STATE.INIT;
    }

    public void bucket (){
        clawWrist.setPosition(.42-amt);
        state = STATE.BUCKET;
    }
    public void inspecTransfer(){
        clawWrist.setPosition(0.85-amt);
        state = STATE.INSPECTRANSFER;
    }
    public void inspec() {

        clawWrist.setPosition(0.43- amt);
        state = STATE.INSPEC;
    }

    public void lowOutspec() {
        clawWrist.setPosition(0.29-amt);
        state = STATE.LOWOUTSPEC;
    }
    public void highOutSpec(){
        clawWrist.setPosition(0.39+amt);//0.52
        state = STATE.HIGHOUTSPEC;
    }

    @Override
    public void telemetry(Telemetry telemetry){
        telemetry.addData("Claw wrist pos ", clawWrist.getPosition());
        telemetry.addData("Claw wrist state ", state);
    }

    @Override
    public void update() {

    }
}
