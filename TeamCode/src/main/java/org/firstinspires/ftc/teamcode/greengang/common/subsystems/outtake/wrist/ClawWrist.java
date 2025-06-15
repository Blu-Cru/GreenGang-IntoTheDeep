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
    double parallel = .45;
    public STATE state;
    double vMax,aMax=4;

    public ClawWrist(HardwareMap hardwareMap) {
        clawWrist =  hardwareMap.get(Servo.class, "outtake wrist");
        state = STATE.INIT;
    }

    @Override
    public void init() {
        clawWrist.setPosition(parallel + 0.22);
//        mp=new MotionProfile(parallel+.2, clawWrist.getPosition(), vMax,aMax).start();
        state = STATE.INIT;
    }

    public void bucket (){
        clawWrist.setPosition(parallel - 0.1);
//        mp=new MotionProfile(parallel-.1, clawWrist.getPosition(), vMax,aMax).start();
        state = STATE.BUCKET;
    }
    public void inspecTransfer(){
        clawWrist.setPosition(parallel + 0.4);
//        mp=new MotionProfile(parallel+.4, clawWrist.getPosition(), vMax,aMax).start();
        state = STATE.INSPECTRANSFER;
    }
    public void inspec() {
        clawWrist.setPosition(parallel-0.03);
//        mp=new MotionProfile(parallel-.03, clawWrist.getPosition(), vMax,aMax).start();
        state = STATE.INSPEC;
    }

    public void lowOutspec() {
        clawWrist.setPosition(parallel); // not tuned
//        mp=new MotionProfile(.29- parallel, clawWrist.getPosition(), vMax,aMax).start();
        state = STATE.LOWOUTSPEC;
    }
    public void highOutSpec(){
        clawWrist.setPosition(parallel+0.2);
//        mp=new MotionProfile(parallel+.1, clawWrist.getPosition(), vMax,aMax).start();
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
