package org.firstinspires.ftc.teamcode.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;

public class WristRotationServo implements GreenSubsystem, Subsystem {
    private Servo wristRotServo;
    private double pos = 0.3;

    private enum STATE {
        INIT,
        FLIPPED,
        NINETY
    }

    private STATE currentState = STATE.INIT;
    private STATE targetState = STATE.INIT;

    public WristRotationServo(HardwareMap hardwareMap) {
        wristRotServo = hardwareMap.get(Servo.class, "claw wrist");
        wristRotServo.setPosition(pos);
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
        pos = Range.clip(pos + increment, 0.0, 1.0);
        wristRotServo.setPosition(pos);
        targetState = null;
        currentState = null;
    }

    @Override
    public void init() {
        wristRotServo.setPosition(0.3);
        currentState = STATE.INIT;
        targetState = STATE.INIT;
    }

    @Override
    public void update() {
        if (targetState != null && currentState != targetState) {
            switch (targetState) {
                case INIT:
                    wristRotServo.setPosition(0.3);
                    break;
                case FLIPPED:
                    wristRotServo.setPosition(0.85);
                    break;
                case NINETY:
                    wristRotServo.setPosition(0.55);
                    break;
            }
            currentState = targetState;
        }

        pos = wristRotServo.getPosition();
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Wrist Position", pos);
        telemetry.addData("Current State", currentState);
        telemetry.addData("Target State", targetState);
    }
}
