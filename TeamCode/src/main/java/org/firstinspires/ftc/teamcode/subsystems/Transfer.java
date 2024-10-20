package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Transfer {
    public Servo claw;
    private ServoControllerEx clawController;
    public Transfer(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "clawServo");
    }

    public void init() {
        clawController = (ServoControllerEx) claw.getController();
        claw.setPosition(0);
    }

    public void clawSetPower(double pos){
        clawController.setServoPwmEnable(claw.getPortNumber());
        claw.setPosition(pos);
    }
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("claw power", claw.getPosition());
    }
}
