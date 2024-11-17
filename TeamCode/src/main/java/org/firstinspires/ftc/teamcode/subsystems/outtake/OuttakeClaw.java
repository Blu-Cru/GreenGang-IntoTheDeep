package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class OuttakeClaw implements Subsystem {
    Servo outtakeClaw;

    private ServoControllerEx outtakeClawController;

    public OuttakeClaw(HardwareMap hardwareMap) {
        outtakeClaw = hardwareMap.get(Servo.class, "outtake claw");
    }

    public void init() {
        outtakeClawController = (ServoControllerEx) outtakeClaw.getController();
        //outtakeClaw.setPosition(1);
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    public void close(){
        outtakeClaw.setPosition(0.45);//fix
    }
    public void open() {
        outtakeClaw.setPosition(0.8);
    }
    public void outtakeClawSetPos(double pos){
        outtakeClawController.setServoPwmEnable(outtakeClaw.getPortNumber());
        outtakeClaw.setPosition(pos);
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("outtake claw power", outtakeClaw.getPosition());
    }


}
