package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;
public class IntakeWrist implements GreenSubsystem {

    double wristPos;
    Servo wrist;

    public IntakeWrist(HardwareMap hardwareMap) {
        wrist = hardwareMap.get(Servo.class, "wrist");
    }

    public void init() {
        wrist.setPosition(0); // todo: could be wrong
    }

    public void parallel() {
        wrist.setPosition(0.8); // todo: test this
    }

    public void down(){
        wrist.setPosition(0.45); // todo: test this
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("intake wrist pos ", wristPos);
    }

    public void update() {
        wristPos = wrist.getPosition();
    }
}