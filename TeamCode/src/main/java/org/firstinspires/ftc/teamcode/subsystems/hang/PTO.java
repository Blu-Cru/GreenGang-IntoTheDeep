package org.firstinspires.ftc.teamcode.subsystems.hang;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;

public class PTO implements GreenSubsystem, Subsystem {

    Servo pto;
    State state;

    public enum State {
        ENGAGED,
        DISENGAGED;
    }

    public PTO(HardwareMap hardwareMap){
        pto = hardwareMap.get(Servo.class, "pto");
        state = State.DISENGAGED;
    }

    @Override
    public void init() {

    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("PTO state ", state);
    }

    @Override
    public void update() {

    }

    public void engagePTO(){
        pto.setPosition(1); // may not be 1 double check
        state = State.ENGAGED;
    }

    public void disengagePTO(){
        pto.setPosition(0); // may not be 0 double check
        state = State.DISENGAGED;
    }

}
