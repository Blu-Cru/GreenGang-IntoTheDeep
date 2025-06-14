package org.firstinspires.ftc.teamcode.greengang.common.subsystems.outtake.arm;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.greengang.common.util.SmoothServo;

@Config
public abstract class ArmServo extends SmoothServo {
    public static double aMax = 5.0, vMax=4.0;
    public ArmServo(String name) {super(name, vMax, aMax );}

    void setAngle(double deg) {
        double pos = (deg - 90.0)*getTicksPerDeg() + getVerticalPos();
        setPosition(pos);
    }

    abstract double getVerticalPos();
    abstract double getTicksPerDeg();
}