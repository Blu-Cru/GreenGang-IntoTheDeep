package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoImpl;

public class IntakeWristServo extends ServoImpl {
    public IntakeWristServo(HardwareMap hwMap) {
        this(hwMap.get(ServoImpl.class, "intakeWrist"));
    }

    private IntakeWristServo (ServoImpl servo) {
        super(servo.getController(), servo.getPortNumber());
    }

    public void dropToIntake() {
        setPosition(0.5);
    }
    public void transferPassthrough(){
        setPosition(0.15);
    }
}
