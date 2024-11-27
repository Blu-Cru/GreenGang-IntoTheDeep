package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class IntakeWrist implements Subsystem {

    Servo wrist;
    private ServoControllerEx intakeWristController;

    public IntakeWrist(HardwareMap hardwareMap) {
        wrist = hardwareMap.get(Servo.class, "wrist");
        //wrist.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void init() {
        wrist.setPosition(0.75); // could be wrong
    }

    public void transfer() {
        wrist.setPosition(0.4);
    }//update with the bucket

    public void intake(){
        wrist.setPosition(0.75);
    }

    public String telemetry(Telemetry telemetry){
        telemetry.addData("intake wrist power", wrist.getPosition());
        return null;
    }
    @Override
    public void read() {

    }

    @Override
    public void write() {
    }
}
