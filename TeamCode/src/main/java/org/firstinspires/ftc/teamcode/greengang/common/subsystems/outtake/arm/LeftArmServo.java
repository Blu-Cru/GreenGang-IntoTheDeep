package org.firstinspires.ftc.teamcode.greengang.common.subsystems.outtake.arm;

public class LeftArmServo extends ArmServo {
    public LeftArmServo() {
        super("arm left");
    }

    @Override
    double getVerticalPos() {
        // TODO: Get the vertical position of the arm
        return 0.4;
    }

    @Override
    double getTicksPerDeg() {
        // TODO: find tick delta for 90 degrees REMEMBER THIS IS +/-   ticks of 90 degrees - ticks of 0 degrees / 90 degrees
        return -0.28 / 90.0;
    }
}