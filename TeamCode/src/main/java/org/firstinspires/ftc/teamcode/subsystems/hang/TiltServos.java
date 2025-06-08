package org.firstinspires.ftc.teamcode.subsystems.hang;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;

public class TiltServos implements GreenSubsystem, Subsystem {
    Servo rightTilt;
    Servo leftTilt;

    public TiltServos(HardwareMap hardwareMap) {
        rightTilt = hardwareMap.get(Servo.class, "right tilt");
        leftTilt = hardwareMap.get(Servo.class, "left tilt");
    }

    public void tilt() {
        leftTilt.setPosition(1);
        rightTilt.setPosition(1);
    }

    public void untilt() {
        leftTilt.setPosition(0);
        rightTilt.setPosition(0);
    }

    @Override
    public void init() {
        untilt();
    }

    @Override
    public void telemetry(Telemetry telemetry) {

    }

    @Override
    public void update() {

    }

}
