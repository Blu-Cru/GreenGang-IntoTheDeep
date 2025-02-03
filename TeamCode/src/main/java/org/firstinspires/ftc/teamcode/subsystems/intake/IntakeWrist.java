package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;
public class IntakeWrist implements GreenSubsystem, Subsystem {

    double wristPos;
    Servo wrist;

    public IntakeWrist(HardwareMap hardwareMap) {
        wrist = hardwareMap.get(Servo.class, "wrist");
    }

    public void init() {
        wrist.setPosition(0.8);
    }

    public void parallel() {
        wrist.setPosition(0.8);
    }

    public void down(){
        wrist.setPosition(0.5);
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("intake wrist pos ", wristPos);
    }

    public void update() {
        wristPos = wrist.getPosition();
    }
}