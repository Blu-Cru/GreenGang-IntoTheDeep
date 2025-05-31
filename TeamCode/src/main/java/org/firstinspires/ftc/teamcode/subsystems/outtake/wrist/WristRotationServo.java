package org.firstinspires.ftc.teamcode.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.util.SmoothServo;

public class WristRotationServo extends SmoothServo implements GreenSubsystem, Subsystem  {
    double pos=0;

    public WristRotationServo() {
        super("claw wrist", false);
    }

    public void flip(){
        setPosition(.5);
    }

    public void manualRotate(double incrementation) {
        setPosition(pos+incrementation);
        pos += incrementation;
    }


    @Override
    public void init() {
        setPosition(0);
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("wrist rot pos:", pos);
    }

    @Override
    public void update() {

    }
}
