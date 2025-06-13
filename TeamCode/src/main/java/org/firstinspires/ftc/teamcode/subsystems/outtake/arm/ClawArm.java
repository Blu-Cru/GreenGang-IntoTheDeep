package org.firstinspires.ftc.teamcode.subsystems.outtake.arm;

import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.util.MotionProfile;

public class ClawArm implements GreenSubsystem, Subsystem {
    public Servo leftArm, rightArm;
    MotionProfile mp,mp2;
    double vertPos = 0.47;
    double aMax = 2, vMax = 4;
    double targetPos, middlePos, firstPos;
    public enum STATE {
        INIT,
        OUTSAMPLE,
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
        transfer();
    }

    public void transfer() { // Transfer
        targetPos = vertPos +0.4;
        mp = new MotionProfile(targetPos, leftArm.getPosition(), vMax, aMax).start();
        mp2 = new MotionProfile(targetPos, rightArm.getPosition(), vMax, aMax).start();

        state = STATE.INIT;
    }
    public void sampleOuttake() { // scoring samples
        firstPos = vertPos-0.15;
        middlePos = vertPos;
        targetPos = vertPos -0.15;
//        leftArm.setPosition(targetPos);
        rightArm.setPosition(firstPos);

        rightArm.setPosition(middlePos);
        rightArm.setPosition(targetPos);
//        mp = new MotionProfile(vertPos, leftArm.getPosition(), vMax, aMax).start();
//        mp2 = new MotionProfile(vertPos, rightArm.getPosition(), vMax, aMax).start();
//        mp = new MotionProfile(targetPos, leftArm.getPosition(), vMax, aMax).start();
//        mp2 = new MotionProfile(targetPos, rightArm.getPosition(), vMax, aMax).start();

        state = STATE.OUTSAMPLE;
    }
    public void specOuttake(){ //scoring specimen
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
        if (mp != null && !mp.done()) {
            double leftPos = mp.getInstantTargetPosition();
            leftArm.setPosition(leftPos);
        }

        if (mp2 != null && !mp2.done()) {
            double rightPos = mp2.getInstantTargetPosition();
            rightArm.setPosition(rightPos);
        }
    }

}
