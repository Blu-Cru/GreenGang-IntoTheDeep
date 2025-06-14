package org.firstinspires.ftc.teamcode.greengang.common.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.common.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.greengang.common.util.MotionProfile;

public class Turret implements GreenSubsystem, Subsystem {
    private Servo turret;
    private double pos = 0.3;
    private MotionProfile mp;
    private double vMax,aMax=2;

    private enum STATE {
        INIT,
        FLIPPED,
        NINETY
    }

    private STATE currentState = STATE.INIT;
    private STATE targetState = STATE.INIT;

    public Turret(HardwareMap hardwareMap) {
        turret = hardwareMap.get(Servo.class, "turret");
        turret.setPosition(pos);
    }

    public void flip() {
        if (targetState == STATE.INIT) {
            targetState = STATE.FLIPPED;
        } else if (targetState == STATE.FLIPPED || targetState == STATE.NINETY) {
            targetState = STATE.INIT;
        }
        else{
            targetState = STATE.INIT;
        }
    }

    public void turn90() {
        if (targetState != STATE.NINETY) {
            targetState = STATE.NINETY;
        } else {
            targetState = STATE.INIT;
        }
    }

    public void manualRotate(double increment) {
        pos = Range.clip(pos + increment, 0.3, 0.85);
        turret.setPosition(pos);
        targetState = null;
        currentState = null;
    }

    @Override
    public void init() {
        turret.setPosition(0.3);
        currentState = STATE.INIT;
        targetState = STATE.INIT;
    }

    @Override
    public void update() {
        if (targetState != null && currentState != targetState) {
            switch (targetState) {
                case INIT:
                    turret.setPosition(0.3);
//                    mp = new MotionProfile(0.3, pos, vMax, aMax).start();
                    break;
                case FLIPPED:
                    turret.setPosition(.85);
//                    mp = new MotionProfile(.85, pos,vMax,aMax).start();
                    break;
                case NINETY:
                    turret.setPosition(.55);
//                    mp = new MotionProfile(.55, pos, vMax,aMax).start();
                    break;
            }
            currentState = targetState;
        }

        if (mp != null && !mp.done()) {
            double leftPos = mp.getInstantTargetPosition();
            turret.setPosition(leftPos);
        }

        pos = turret.getPosition();
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Wrist Position", pos);
        telemetry.addData("Current State", currentState);
        telemetry.addData("Target State", targetState);
    }
}
