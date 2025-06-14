package org.firstinspires.ftc.teamcode.subsystems.outtake.arm;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.subsystems.util.SmoothServo;

@Config
public abstract class ArmServo extends SmoothServo {
    public static double aMax = 2.0, vMax=3.0;
    public ArmServo(String name) {super(name, vMax, aMax );}

    void setAngle(double deg) {
        double pos = (deg - 90.0)*getTicksPerDeg() + getVerticalPos();
        setPosition(pos);
    }

    abstract double getVerticalPos();
    abstract double getTicksPerDeg();
}
