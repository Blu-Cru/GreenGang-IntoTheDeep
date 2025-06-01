package org.firstinspires.ftc.teamcode.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.util.SmoothServo;

public class WristRotationServo implements GreenSubsystem, Subsystem  {
    double pos=0;
    Servo WristRotServo;

    STATE state;
    boolean isDone = false;

    enum STATE {
        NINETY,
        FLIPPED,
        INIT
    }
    public WristRotationServo(HardwareMap hardwareMap) {
        WristRotServo = hardwareMap.get(Servo.class, "claw wrist");
        state = STATE.INIT;
    }

    public void flip(){
        if(state == STATE.INIT) {
            WristRotServo.setPosition(0.85);
            if(WristRotServo.getPosition() ==0.85){
                state = STATE.FLIPPED;
            }

        } else {
            WristRotServo.setPosition(0.3);
            if(WristRotServo.getPosition() == 0.3){
                state = STATE.INIT;
            }

        }
    }

    public void manualRotate(double incrementation) {
        WristRotServo.setPosition(Range.clip(pos + incrementation, 0, 1));
    }
    public void turn90(){
        if (state == STATE.NINETY){
            WristRotServo.setPosition(.3);
            state = STATE.INIT;
        } else {
            WristRotServo.setPosition(0.55);
            state = STATE.NINETY;
        }
    }


    @Override
    public void init() {
        WristRotServo.setPosition(0.3);
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("wrist rot pos:", pos);
    }


    @Override
    public void update() {
        pos = WristRotServo.getPosition();
    }
}
