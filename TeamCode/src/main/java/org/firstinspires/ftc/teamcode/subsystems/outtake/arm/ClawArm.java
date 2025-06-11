package org.firstinspires.ftc.teamcode.subsystems.outtake.arm;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;

public class ClawArm implements GreenSubsystem, Subsystem {
    public Servo leftArm, rightArm;
    public enum STATE {
        INIT,
        PERP,
        INSPEC,
        OUTSPEC;
    }

    public STATE state;
    public ClawArm(HardwareMap hardwareMap) {
        leftArm = hardwareMap.get(Servo.class, "arm left");
        rightArm = hardwareMap.get(Servo.class, "arm right");

        state = STATE.INIT;
    }

    public void init() {
        intake();
    }

    public void intake() { // Transfer
        leftArm.setPosition(.9); //0.08
        rightArm.setPosition(.9);
        state = STATE.INIT;
    }
    public void perpendicular() { // scoring samples
        leftArm.setPosition(.7);
        rightArm.setPosition(.7);
        state = STATE.PERP;
    }
    public void vert(){ //scoring specimen
        leftArm.setPosition(.4);
        rightArm.setPosition(.4);
        state = STATE.OUTSPEC;
    }

    public void inspec() {
        leftArm.setPosition(.85); //pos + 0.38
        rightArm.setPosition(.85);
        state = STATE.INSPEC;
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Claw arm pos ", leftArm.getPosition());
        telemetry.addData("Claw arm state ", state);
    }

    @Override
    public void update() {

    }

}
