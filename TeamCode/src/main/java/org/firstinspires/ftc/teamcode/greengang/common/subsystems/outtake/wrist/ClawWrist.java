package org.firstinspires.ftc.teamcode.greengang.common.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.common.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.greengang.common.util.MotionProfile;

public class ClawWrist implements GreenSubsystem, Subsystem {
    Servo clawWrist;
    MotionProfile mp;
    public enum STATE {
        INIT,
        BUCKET,
        LOWOUTSPEC,
        HIGHOUTSPEC,
        INSPECTRANSFER,
        INSPEC,

    }
    double amt = 0.20;
    public STATE state;
    double vMax,aMax=4;

    public ClawWrist(HardwareMap hardwareMap) {
        clawWrist =  hardwareMap.get(Servo.class, "outtake wrist");
        state = STATE.INIT;
    }

    @Override
    public void init() {
        mp=new MotionProfile(.7-amt, clawWrist.getPosition(), vMax,aMax).start();
        state = STATE.INIT;
    }

    public void bucket (){
        mp=new MotionProfile(.42-amt, clawWrist.getPosition(), vMax,aMax).start();
        state = STATE.BUCKET;
    }
    public void inspecTransfer(){
        mp=new MotionProfile(.85-amt, clawWrist.getPosition(), vMax,aMax).start();
        state = STATE.INSPECTRANSFER;
    }
    public void inspec() {
        mp=new MotionProfile(.43-amt, clawWrist.getPosition(), vMax,aMax).start();
        state = STATE.INSPEC;
    }

    public void lowOutspec() {
        mp=new MotionProfile(.29-amt, clawWrist.getPosition(), vMax,aMax).start();
        state = STATE.LOWOUTSPEC;
    }
    public void highOutSpec(){
        mp=new MotionProfile(.39-amt, clawWrist.getPosition(), vMax,aMax).start();
        state = STATE.HIGHOUTSPEC;
    }

    @Override
    public void telemetry(Telemetry telemetry){
        telemetry.addData("Claw wrist pos ", clawWrist.getPosition());
        telemetry.addData("Claw wrist state ", state);
    }

    @Override
    public void update() {
        if (mp != null && !mp.done()) {
            double leftPos = mp.getInstantTargetPosition();
            clawWrist.setPosition(leftPos);
        }
    }
}
