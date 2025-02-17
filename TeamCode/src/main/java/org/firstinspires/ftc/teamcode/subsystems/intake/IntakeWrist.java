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
    private double parralelPos = 0.77; //value of the intake being parralel with horizontal slides

    public IntakeWrist(HardwareMap hardwareMap) {
        wrist = hardwareMap.get(Servo.class, "wrist");
    }

    public void init() {
        wrist.setPosition(parralelPos);
    }

    public void parallel() {
        wrist.setPosition(parralelPos);
    }

    public void down(){
        wrist.setPosition(parralelPos - 0.25);
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("intake wrist pos ", wristPos);
    }

    public void update() {
        wristPos = wrist.getPosition();
    }
}