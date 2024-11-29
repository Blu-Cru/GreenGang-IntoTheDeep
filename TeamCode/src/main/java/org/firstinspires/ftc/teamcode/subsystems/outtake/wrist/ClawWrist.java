package org.firstinspires.ftc.teamcode.subsystems.outtake.wrist;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class ClawWrist implements Subsystem {
    Servo clawWrist;

    public enum STATE {
        INIT,
        BUCKET,
        SPEC,
        INTK,
        TRANSFER;
    }

    public STATE state;

    public ClawWrist(HardwareMap hardwareMap) {
        clawWrist = hardwareMap.get(Servo.class, "claw wrist");
        state = STATE.INIT;
    }

    public void init() {
    }

    public void intake(){
        clawWrist.setPosition(0.448);
        state = STATE.INTK;
    }
    public void transfer() {
        clawWrist.setPosition(1);
        state = STATE.TRANSFER;
    }
    public void bucket (){
        clawWrist.setPosition(1); // GET NUM
        state = STATE.BUCKET;
    }
    public void spec (){
        clawWrist.setPosition(1);  // GET NUM
        state = STATE.SPEC;
    }

    public String telemetry(Telemetry telemetry){
        telemetry.addData("CLAW WRIST POS ", clawWrist.getPosition());
        telemetry.addData("CLAW WRIST STATE ", state);
        return null;
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }
}
