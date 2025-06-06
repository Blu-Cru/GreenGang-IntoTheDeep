package org.firstinspires.ftc.teamcode.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.util.MotionProfile;

public class WristRotationServo implements GreenSubsystem, Subsystem  {
    double pos=0.3;
    public static double vMax = 2, aMax = 2, xI = 0, xF = 1;
    Servo wristRotServo;
    public MotionProfile mp;
    STATE state;
    private ElapsedTime profileTimer = new ElapsedTime();

    enum STATE {
        NINETY,
        FLIPPED,
        INIT
    }
    public WristRotationServo(HardwareMap hardwareMap) {
        wristRotServo = hardwareMap.get(Servo.class, "claw wrist");
        state = STATE.INIT;
        mp = new MotionProfile(0,0,0,0);
    }

    public void flip(){
        profileTimer.reset();
        if(state == STATE.INIT){
            wristRotServo.setPosition(0.85);
//            mp = new MotionProfile(0.85, wristRotServo.getPosition(), vMax, aMax).start();
            if(profileTimer.seconds() > 1) {
                state = STATE.FLIPPED;
            }
        } else {
            wristRotServo.setPosition(0.3);
//            mp = new MotionProfile(0.3, wristRotServo.getPosition(), vMax, aMax).start();
            if(profileTimer.seconds()>1) {
                state = STATE.INIT;
            }
        }


    }

    public void turn90(){
        profileTimer.reset();
        if (state != STATE.NINETY){
            wristRotServo.setPosition(0.55);
//            mp = new MotionProfile(0.55, wristRotServo.getPosition(), vMax, aMax).start();
            if(profileTimer.seconds()>1) {
                state = STATE.NINETY;
            }
        }
        else {
            wristRotServo.setPosition(0.3);
//            mp = new MotionProfile(0.3, wristRotServo.getPosition(), vMax,aMax).start();
            if(profileTimer.seconds()>1) {
                state = STATE.INIT;
            }
        }
    }

    public void manualRotate(double incrementation) {
        wristRotServo.setPosition(Range.clip(pos + incrementation, 0, 1));
    }


    @Override
    public void init() {
        wristRotServo.setPosition(0.3);
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("wrist rot pos:", pos);
        telemetry.addData("state", state);
        telemetry.addData("timer", profileTimer.seconds());
    }


    @Override
    public void update() {
        pos = wristRotServo.getPosition();

//        if (mp != null && !mp.done()) {
//            double target = mp.getInstantTargetPosition();
//            wristRotServo.setPosition(target);
//        }
    }

//    public void setPosition(double pos){
//        mp = new MotionProfile(this.pos, pos, 4, 4).start();
//    }
}
