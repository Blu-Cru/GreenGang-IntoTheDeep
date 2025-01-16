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
        PERP,
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
        clawArm.setPosition(0.89);
        state = STATE.INIT;
    }
    public void perpendicular() { // scoring
        clawArm.setPosition(0);
        state = STATE.PERP;
    }
    public void vert(){
        clawArm.setPosition(0.5);
        state = STATE.OUTSPEC;
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Claw arm pos ", clawArm.getPosition());
        telemetry.addData("Claw arm state ", state);
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
