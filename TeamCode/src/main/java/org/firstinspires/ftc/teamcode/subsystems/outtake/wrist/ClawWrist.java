package org.firstinspires.ftc.teamcode.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.util.MotionProfile;

public class ClawWrist implements GreenSubsystem, Subsystem {
    Servo clawWrist;
    double aMax = 2, vMax = 4;

    public enum STATE {
        INIT,
        BUCKET,
        SPEC
    }

    public STATE state;
    MotionProfile mp;

    public ClawWrist(HardwareMap hardwareMap) {
        clawWrist =  hardwareMap.get(Servo.class, "outtake wrist");;
        state = STATE.INIT;
    }

    @Override
    public void init() {
        mp = new MotionProfile(0.7, clawWrist.getPosition(),vMax,aMax ).start();
        state = STATE.INIT;
    }

    public void bucket (){
        mp = new MotionProfile(0.42, clawWrist.getPosition(),vMax,aMax ).start();
        state = STATE.BUCKET;
    }

    public void inspec() {
        clawWrist.setPosition(0.45);
    }

    public void Spec() {
        mp = new MotionProfile(0.29, clawWrist.getPosition(),vMax,aMax ).start();
        state = STATE.SPEC;
    }

    @Override
    public void telemetry(Telemetry telemetry){
        telemetry.addData("Claw wrist pos ", clawWrist.getPosition());
        telemetry.addData("Claw wrist state ", state);
    }

    @Override
    public void update() {

    }
}
