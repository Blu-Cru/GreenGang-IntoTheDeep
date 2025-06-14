package org.firstinspires.ftc.teamcode.subsystems.outtake.arm;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.util.MotionProfile;

import java.util.List;

public class ClawArm implements GreenSubsystem, Subsystem {
    ArmServo[] armServos;
    public enum STATE {
        INIT,
        OUTSAMPLE,
        INSPEC,
        OUTSPEC;
    }

    public STATE state;
    public ClawArm(HardwareMap hardwareMap) {
        armServos = new ArmServo[]{new LeftArmServo(), new RightArmServo()};
        state = STATE.INIT;
    }

    public void init() {
        transfer();
    }

    public void transfer() { // Transfer
//        targetPos = vertPos +0.4;
        setAngle(-45.0);

        state = STATE.INIT;
    }
    public void sampleOuttake() { // scoring samples
//        targetPos = vertPos -0.15;
        setAngle(135.0);
        state = STATE.OUTSAMPLE;
    }
    public void specOuttake(){ //scoring specimen
//        targetPos = vertPos -0.25;
        setAngle(135.0);
        state = STATE.OUTSPEC;
    }

    public void inSpec() {
//        targetPos = vertPos +0.35;
        setAngle(15.0);
        state = STATE.INSPEC;
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Claw arm state ", state);
        for(ArmServo armServo : armServos) {
            armServo.telemetry();
        }
    }

    @Override
    public void update() {
        for(ArmServo armServo : armServos) {
            armServo.read();
            armServo.write();
        }
    }
    public void setAngle(double degrees){
        for(ArmServo armServo : armServos) {
            armServo.setAngle(degrees);
        }
    }

}
