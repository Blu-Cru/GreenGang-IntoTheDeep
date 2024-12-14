package org.firstinspires.ftc.teamcode.subsystems.outtake.arm;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class ClawArm implements Subsystem {
    public Servo clawArm;

    public enum STATE {
        INIT,
        BUCKET,
        OUTSPEC,
        INSPEC;
    }

    public STATE state;
    public ClawArm(HardwareMap hardwareMap) {
        clawArm = hardwareMap.get(Servo.class, "claw arm");
        state = STATE.INIT;
    }

    public void init() {
        //claw.setPosition(1);
    }

    public void intake() {
        clawArm.setPosition(0.52); //fix
        state = STATE.INIT; // double check
    }
    public void bucket() {
        clawArm.setPosition(0.1); //fix
        state = STATE.BUCKET;
    }

    public void outSpec(){
        clawArm.setPosition(0); // get num
        state = STATE.OUTSPEC;
    }

    public void inSpec(){
        // sum
        state = STATE.INSPEC;
    }

    private void clawWristSetPos(double pos){
        clawArm.setPosition(pos);
    }

    public String telemetry(Telemetry telemetry) {
        telemetry.addData("CLAW ARM POS: ", clawArm.getPosition());
        telemetry.addData("CLAW ARM STATE: ", state);
        return null;
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

}
