package org.firstinspires.ftc.teamcode.subsystems.util;

import com.qualcomm.robotcore.hardware.Servo;

public class SmoothServo {
    static final double defaultVmax = 3, defaultAmax = 4;

    MotionProfile motionProfile;
    double finalPosition, currentPosition;
    double vMax, aMax;
    public Servo servo;

    public SmoothServo() {
        this(defaultVmax, defaultAmax);
    }

    public SmoothServo(double vMax, double aMax) {
        this.vMax = vMax;
        this.aMax = aMax;
    }


    public void setPosition(double position) {
        finalPosition = position;
        if(motionProfile == null) currentPosition = position;
        motionProfile = new MotionProfile(finalPosition, currentPosition, vMax, aMax).start();
    }


    public void telemetry() {
        Globals.tele.addData("Target Pos: ", finalPosition);
        Globals.tele.addData("Current Pos: ", currentPosition);
    }
}