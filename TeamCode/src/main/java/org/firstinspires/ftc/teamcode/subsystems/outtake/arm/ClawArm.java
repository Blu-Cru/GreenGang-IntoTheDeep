package org.firstinspires.ftc.teamcode.subsystems.outtake.arm;

import static org.firstinspires.ftc.teamcode.opmodes.test.MotionProfileTest.aMax;
import static org.firstinspires.ftc.teamcode.opmodes.test.MotionProfileTest.vMax;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.util.MotionProfile;

public class ClawArm implements GreenSubsystem, Subsystem {
    public Servo clawArm;
    MotionProfile mp;

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
        mp = new MotionProfile(.9, clawArm.getPosition(), vMax*4, aMax*4).start();
        state = STATE.INIT;
    }
    public void perpendicular() { // scoring samples
        mp = new MotionProfile(.4, clawArm.getPosition(), vMax*4, aMax*4).start();
        state = STATE.PERP;
    }
    public void vert(){ //scoring specimen

        mp = new MotionProfile(.4, clawArm.getPosition(), vMax*4, aMax*4).start();
        clawArm.setPosition(.4);
        state = STATE.OUTSPEC;
    }

    public void inspec() {
        mp = new MotionProfile(.85, clawArm.getPosition(), vMax*4, aMax*4).start();
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
