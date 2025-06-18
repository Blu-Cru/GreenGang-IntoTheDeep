package org.firstinspires.ftc.teamcode.greengang.common.subsystems.intake;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.common.util.GreenSubsystem;
public class IntakeWrist implements GreenSubsystem, Subsystem {

    double wristPos;
    Servo wrist;
    private double parallelPos = 0.32; //value of the intake being parallel with horizontal slides

    public IntakeWrist(HardwareMap hardwareMap) {
        wrist = hardwareMap.get(Servo.class, "intk wrist");
    }

    public void init() {
        wrist.setPosition(parallelPos);
    }
    public void transfer(){
        wrist.setPosition(parallelPos - 0.13);
    }

    public void parallel() {
        wrist.setPosition(parallelPos);
    }

    public void down(){
        wrist.setPosition(parallelPos + 0.18);
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("intake wrist pos ", wristPos);
    }

    public void update() {
        wristPos = wrist.getPosition();
    }
}