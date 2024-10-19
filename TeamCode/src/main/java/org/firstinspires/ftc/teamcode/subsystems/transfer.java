package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class transfer {
    public Servo claw;
    private ServoControllerEx clawController;
    public static final double depositPos = 0.89;
    public static final double intakePos = 0.17;
    public transfer(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "clawServo");
    }

    public void init() {
        clawController = (ServoControllerEx) claw.getController();
    }

    public void wristSetPos(double pos){
        clawController.setServoPwmEnable(claw.getPortNumber());
        claw.setPosition(pos);
    }
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("claw power", claw.setPosition());

    }
}
