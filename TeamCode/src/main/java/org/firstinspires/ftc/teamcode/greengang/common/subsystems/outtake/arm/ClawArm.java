package org.firstinspires.ftc.teamcode.greengang.common.subsystems.outtake.arm;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.common.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.greengang.common.util.MotionProfile;


public class ClawArm implements GreenSubsystem, Subsystem {
    public Servo leftArm, rightArm;
    MotionProfile mp,mp2;

    double vertPos = 0.47;
    double aMax = 2, vMax = 2;
    public enum STATE {
        INIT,
        OUTSAMPLE,
        INSPEC,
        OUTSPEC,
        INSPEC_TRANSFER;
    }

    public STATE state;
    public ClawArm(HardwareMap hardwareMap) {
        leftArm = hardwareMap.get(Servo.class, "arm left");
        rightArm = hardwareMap.get(Servo.class, "arm right");
        leftArm.setDirection(Servo.Direction.FORWARD);
        rightArm.setDirection(Servo.Direction.REVERSE);
        state = STATE.INIT;
    }

    public void init() {
        transfer();
    }

    public void transfer() { // Transfer
        double targetPos = vertPos +0.4;
        mp = new MotionProfile(targetPos, leftArm.getPosition(), vMax, aMax).start();
        mp2 = new MotionProfile(targetPos, rightArm.getPosition(), vMax, aMax).start();

        state = STATE.INIT;
    }
    public void outSample() { // scoring samples

        double targetPos = vertPos -0.15;
        mp = new MotionProfile(targetPos, leftArm.getPosition(), vMax, aMax).start();
        mp2 = new MotionProfile(targetPos, rightArm.getPosition(), vMax, aMax).start();
        state = STATE.OUTSAMPLE;
    }
    public void outSpec(){ //scoring specimen
        double targetPos = vertPos -0.24;
        mp = new MotionProfile(targetPos, leftArm.getPosition(), vMax, aMax).start();
        mp2 = new MotionProfile(targetPos, rightArm.getPosition(), vMax, aMax).start();
        state = STATE.OUTSPEC;
    }

    public void inspec() {
        double targetPos = vertPos +0.35;
        mp = new MotionProfile(targetPos, leftArm.getPosition(), vMax, aMax).start();
        mp2 = new MotionProfile(targetPos, rightArm.getPosition(), vMax, aMax).start();
        state = STATE.INSPEC;
    }

    public void inspecTransfer(){
        double targetPos = vertPos+.27;
        mp = new MotionProfile(targetPos, leftArm.getPosition(), vMax, aMax).start();
        mp2 = new MotionProfile(targetPos, rightArm.getPosition(), vMax, aMax).start();
        state=STATE.INSPEC_TRANSFER;
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Claw arm pos ", leftArm.getPosition());
        telemetry.addData("Claw arm state ", state);
        telemetry.addData("left arm position ", leftArm.getPosition());
        telemetry.addData("right arm position ", rightArm.getPosition());
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
