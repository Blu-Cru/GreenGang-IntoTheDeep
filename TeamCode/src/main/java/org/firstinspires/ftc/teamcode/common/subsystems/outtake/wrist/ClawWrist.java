package org.firstinspires.ftc.teamcode.common.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.subsystems.GreenSubsystem;

public class ClawWrist implements GreenSubsystem, Subsystem {
    Servo clawWrist;
    double pos =0.5; // claw angle is parralel to arm

    public enum STATE {
        INIT,
        BUCKET,
        SPECINIT,
        SPEC;
    }

    public STATE state;

    public ClawWrist(HardwareMap hardwareMap) {
        clawWrist = hardwareMap.get(Servo.class, "claw wrist");
        state = STATE.INIT;
    }

    @Override
    public void init() {
        clawWrist.setPosition(0.31); // 0.36
        state = STATE.INIT;
    }

    public void bucket (){
        clawWrist.setPosition(pos); // .5
        state = STATE.BUCKET;
    }
    public void specInit(){
        clawWrist.setPosition(0);
        state = STATE.SPECINIT;
    }

    public void inspec() {

        clawWrist.setPosition(0.35); // .35
    }
    public void Spec() {
        clawWrist.setPosition(0.33); // .27
        state = STATE.SPEC;
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
