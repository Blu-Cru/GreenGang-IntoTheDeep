package org.firstinspires.ftc.teamcode.greengang.common.subsystems.outtake.outtake;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.common.util.GreenSubsystem;

public class OuttakeClaw implements GreenSubsystem, Subsystem {
    Servo outtakeClaw;
    public enum STATE {
        OPEN,
        LOOSE_CLOSE,
        CLOSE;
    }

    public STATE state;

    public OuttakeClaw(HardwareMap hardwareMap) {
        outtakeClaw = hardwareMap.get(Servo.class, "claw");
        state = STATE.OPEN;
    }

    public void close(){
        outtakeClaw.setPosition(0.55);
        state = STATE.CLOSE;
    }
    public void open() {
        outtakeClaw.setPosition(.3);
        state = STATE.OPEN;
    }

    public void looseClose(){
        outtakeClaw.setPosition(.45);
        state = STATE.LOOSE_CLOSE;
    }
    public void toggleLoose() {
        if (state == STATE.LOOSE_CLOSE) {
            open();
        } else if (state == STATE.OPEN) {
            looseClose();
        }
    }
    public void toggle(){
        if(state == STATE.OPEN){
            close();
        }
        else if(state == STATE.CLOSE){
            open();
        }
    }

    @Override
    public void telemetry(Telemetry telemetry){
//        telemetry.addData("Outk claw pos ", outtakeClaw.getPosition());
        telemetry.addData("Outk claw state ", state);
    }

    @Override
    public void update() {

    }

    @Override
    public void init() {
        close();
    }

}
