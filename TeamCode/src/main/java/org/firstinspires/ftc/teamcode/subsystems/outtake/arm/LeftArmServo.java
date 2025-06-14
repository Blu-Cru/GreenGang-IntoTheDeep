package org.firstinspires.ftc.teamcode.subsystems.outtake.arm;

public class LeftArmServo extends ArmServo {
    public LeftArmServo() {
        super("arm left");
    }

    @Override
    double getVerticalPos() {
        // TODO: Get the vertical position of the arm
        return 0.47;
    }

    @Override
    double getTicksPerDeg() {
        // TODO: find tick delta for 90 degrees REMEMBER THIS IS +/-
        return 1.0 / 255.0;
    }
}
