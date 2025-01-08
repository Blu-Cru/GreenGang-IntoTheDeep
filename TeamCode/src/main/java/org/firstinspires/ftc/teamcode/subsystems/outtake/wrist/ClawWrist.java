package org.firstinspires.ftc.teamcode.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

public class ClawWrist implements GreenSubsystem, Subsystem {
    Servo clawWrist;

    public enum STATE {
        INIT,
        BUCKET,
        INTK,
        OUTSPEC,
        INSPEC,
        TRANSFER;
    }

    public STATE state;

    public ClawWrist(HardwareMap hardwareMap) {
        clawWrist = hardwareMap.get(Servo.class, "claw wrist");
        state = STATE.INIT;
    }

    public void init() {
        clawWrist.setPosition(0.143);
        state = STATE.INIT;
    }

    public void intake(){
        clawWrist.setPosition(0.143);
        state = STATE.INTK;
    }
    public void transfer() {
        clawWrist.setPosition(.65);
        state = STATE.TRANSFER;
    }
    public void bucket (){
        clawWrist.setPosition(.65); // GET NUM
        state = STATE.BUCKET;
    }
    public void outSpec(){
        clawWrist.setPosition(.8);  // GET NUM
        state = STATE.OUTSPEC;
    }

    public void inSpec(){
        // get num
        state = STATE.INSPEC;
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("Claw wrist pos ", clawWrist.getPosition());
        telemetry.addData("Claw wrist state ", state);
    }

    @Override
    public void update() {

    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }
}
