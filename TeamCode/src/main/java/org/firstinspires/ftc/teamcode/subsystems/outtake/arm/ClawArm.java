package org.firstinspires.ftc.teamcode.subsystems.outtake.arm;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

public class ClawArm implements GreenSubsystem, Subsystem {
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
        intake();
    }

    public void intake() {
        clawArm.setPosition(0.53); //fix
        state = STATE.INIT; // double check
    }
    public void bucket() {
        clawArm.setPosition(0.3); //fix
        state = STATE.BUCKET;
    }

    public void outSpec(){
        clawArm.setPosition(0.5); // get num
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
