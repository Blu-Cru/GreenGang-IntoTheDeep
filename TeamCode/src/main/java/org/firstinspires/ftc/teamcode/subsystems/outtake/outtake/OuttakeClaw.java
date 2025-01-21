package org.firstinspires.ftc.teamcode.subsystems.outtake.outtake;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

public class OuttakeClaw implements GreenSubsystem, Subsystem {
    Servo outtakeClaw;

    public enum STATE {
        OPEN,
        CLOSE;
    }

    public STATE state;

    public OuttakeClaw(HardwareMap hardwareMap) {
        outtakeClaw = hardwareMap.get(Servo.class, "outtake claw");
        state = STATE.CLOSE;
    }

    public void close(){
        outtakeClaw.setPosition(0.53);
        state = STATE.CLOSE;
    }
    public void open() {
        outtakeClaw.setPosition(0.43);
        state = STATE.OPEN;
    }

    @Override
    public void telemetry(Telemetry telemetry){
        telemetry.addData("Outk claw pos ", outtakeClaw.getPosition());
        telemetry.addData("Outk claw state ", state);
    }

    @Override
    public void update() {

    }

    @Override
    public void init() {
        open();
    }

}
