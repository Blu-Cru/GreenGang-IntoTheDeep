package org.firstinspires.ftc.teamcode.subsystems.outtake.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class OuttakeClaw implements Subsystem {
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

    public void init() {
        //outtakeClaw.setPosition(1);
    }

    public void close(){
        outtakeClaw.setPosition(0.49);//fix
        state = STATE.CLOSE;
    }
    public void open() {
        outtakeClaw.setPosition(0.4);
        state = STATE.OPEN;
    }
    public void outtakeClawSetPos(double pos){
        outtakeClaw.setPosition(pos);
    }

    public String telemetry(Telemetry telemetry){
        telemetry.addData("OUTK CLAW POS ", outtakeClaw.getPosition());
        telemetry.addData("OUTK CLAW STATE ", state);
        return null;
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }


}
