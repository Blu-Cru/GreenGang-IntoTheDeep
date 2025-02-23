package org.firstinspires.ftc.teamcode.subsystems.outtake.arm;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

public class ClawArm implements GreenSubsystem, Subsystem {
    public Servo clawArm;
    private double pos = 0.45; //value of the arm being parralel with vertical slides
    public enum STATE {
        INIT,
        PERP,
        INSPEC,
        OUTSPEC;
    }

    public STATE state;
    public ClawArm(HardwareMap hardwareMap) {
        clawArm = hardwareMap.get(Servo.class, "claw arm");
        state = STATE.INIT;
    }

    public void init() {
        intake();
    }

    public void intake() { // Transfer
        clawArm.setPosition(pos - 0.37); //0.08
        state = STATE.INIT;
    }
    public void perpendicular() { // scoring samples
        clawArm.setPosition(pos + 0.2);
        state = STATE.PERP;
    }
    public void vert(){ //scoring specimen
        clawArm.setPosition(pos + 0.48);
        state = STATE.OUTSPEC;
    }

    public void inspec() {
        clawArm.setPosition(pos+0.38); //0.83
        state = STATE.INSPEC;
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Claw arm pos ", clawArm.getPosition());
        telemetry.addData("Claw arm state ", state);
    }

    @Override
    public void update() {

    }

}
