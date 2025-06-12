package org.firstinspires.ftc.teamcode.subsystems.outtake.arm;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.util.MotionProfile;

public class ClawArm implements GreenSubsystem, Subsystem {
    public Servo leftArm, rightArm;
    MotionProfile mp,mp2;
    double vertPos = 0.45;
    double aMax = 0.2, vMax = 0.2;
    double targetPos;
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
        targetPos = vertPos +0.4;
        mp = new MotionProfile(targetPos, leftArm.getPosition(), vMax, aMax).start();
        mp2 = new MotionProfile(targetPos, rightArm.getPosition(), vMax, aMax).start();

        state = STATE.INIT;
    }
    public void perpendicular() { // scoring samples
        targetPos = vertPos +0.15;
        mp = new MotionProfile(targetPos, leftArm.getPosition(), vMax, aMax).start();
        mp2 = new MotionProfile(targetPos, rightArm.getPosition(), vMax, aMax).start();

        state = STATE.PERP;
    }
    public void vert(){ //scoring specimen
        targetPos = vertPos -0.25;
        mp = new MotionProfile(targetPos, leftArm.getPosition(), vMax, aMax).start();
        mp2 = new MotionProfile(targetPos, rightArm.getPosition(), vMax, aMax).start();

        state = STATE.OUTSPEC;
    }

    public void inspec() {
        targetPos = vertPos +0.35;
        mp = new MotionProfile(targetPos, leftArm.getPosition(), vMax, aMax).start();
        mp2 = new MotionProfile(targetPos, rightArm.getPosition(), vMax, aMax).start();

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
