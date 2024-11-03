package org.firstinspires.ftc.teamcode.subsystems.transfer;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class Transfer implements Subsystem {
    public Servo claw;
    private ServoControllerEx clawController;
    public Transfer(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "clawServo");
    }

    public void init() {
        clawController = (ServoControllerEx) claw.getController();
        claw.setPosition(0);
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    public void clawSetPower(double pos){
        clawController.setServoPwmEnable(claw.getPortNumber());
        claw.setPosition(pos);
    }
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("claw power", claw.getPosition());
    }
}
