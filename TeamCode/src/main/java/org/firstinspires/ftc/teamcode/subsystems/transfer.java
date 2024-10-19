package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class transfer {
    Servo claw;


    public transfer(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "clawServo");
    }

    public void init() {
        claw.setPosition(0);
    }


    public void clawSetPower(double power){
        claw.setPosition(power);
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("claw power", claw.setPosition());

    }
}
