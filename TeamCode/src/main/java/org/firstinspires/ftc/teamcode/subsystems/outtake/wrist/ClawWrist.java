package org.firstinspires.ftc.teamcode.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

public class ClawWrist implements GreenSubsystem, Subsystem {
    Servo clawWrist;

    public enum STATE {
        INIT,
        BUCKET,
        SPEC;
    }

    public STATE state;

    public ClawWrist(HardwareMap hardwareMap) {
        clawWrist = hardwareMap.get(Servo.class, "claw wrist");
        state = STATE.INIT;
    }

    @Override
    public void init() {
        clawWrist.setPosition(0.74); // .7
        state = STATE.INIT;
    }

    public void bucket (){
        clawWrist.setPosition(0.9); // .6
        state = STATE.BUCKET;
    }

    public void inspec() {
        clawWrist.setPosition(.95); // .55
    }
    public void Spec() {
        clawWrist.setPosition(.7); // .85
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
