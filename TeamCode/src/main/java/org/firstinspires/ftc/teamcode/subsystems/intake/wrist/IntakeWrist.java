package org.firstinspires.ftc.teamcode.subsystems.intake.wrist;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class IntakeWrist implements Subsystem {

    Servo wrist;

    public enum STATE {
        INIT,
        INTK,
        TRANSFER;
    }

    public STATE state;

    public IntakeWrist(HardwareMap hardwareMap) {
        wrist = hardwareMap.get(Servo.class, "wrist");
        state = STATE.INIT;
    }

    public void init() {
        wrist.setPosition(0.75);
        state = STATE.INIT;
    }

    public void transfer() {
        wrist.setPosition(0.4);
        state = STATE.TRANSFER;
    }

    public void intake(){
        wrist.setPosition(0.75);
        state = STATE.INTK;
    }

    public String telemetry(Telemetry telemetry){
        telemetry.addData("INTK WRIST POS ", wrist.getPosition());
        telemetry.addData("INTK WRIST STATE ", state);
        return null;
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {
    }
}
