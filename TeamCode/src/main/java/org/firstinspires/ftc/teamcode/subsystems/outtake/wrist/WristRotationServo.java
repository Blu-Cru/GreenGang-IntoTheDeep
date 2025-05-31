package org.firstinspires.ftc.teamcode.subsystems.outtake.wrist;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.util.SmoothServo;

public class WristRotationServo implements GreenSubsystem, Subsystem {

    double pos=0;
    Servo wristRotservo;
    public WristRotationServo(HardwareMap hardwareMap) {
        wristRotservo = new SmoothServo("claw wrist", false);
    }

    public void flip(){
        wristRotservo.setPosition(.5);
    }

    public void manualRotate(double incrementation) {
        wristRotservo.setPosition(pos+incrementation);
        pos += incrementation;
    }


    @Override
    public void init() {
        wristRotservo.setPosition(0);
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("wrist rot pos:", pos);
    }

    @Override
    public void update() {

    }
}
