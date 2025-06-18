package org.firstinspires.ftc.teamcode.greengang.common.subsystems.outtake.arm;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.common.util.GreenSubsystem;

import java.util.List;

public class ClawArm implements GreenSubsystem, Subsystem {
    ArmServo[] armServos;
    public enum STATE {
        INIT,
        OUTSAMPLE,
        FLAT,
        INSPEC,
        INSPECTRANSFER,
        OUTSPEC;

    }

    public STATE state;
    public ClawArm(HardwareMap hardwareMap) {
        armServos = new ArmServo[]{new LeftArmServo()};
        // Set higher constraints for faster, immediate movement
//        for (ArmServo armServo : armServos) {
//            armServo.setConstraints(15.0, 30.0); // You can adjust these values as needed
//        }
        state = STATE.INIT;
    }

    public void init() {
        for(ArmServo armServo : armServos) {
            armServo.init();
        }
        transfer();
    }

    public void transfer() { // Transfer
//        targetPos = vertPos +0.4;
        setAngle(-23.0);

        state = STATE.INIT;
    }
    public void flat(){
        setAngle(0);
        state = STATE.FLAT;
    }
    public void sampleOuttake() { // scoring samples
//        targetPos = vertPos -0.15;
        setAngle(120.0);
        state = STATE.OUTSAMPLE;
    }
    public void specOuttake(){ //scoring specimen
//        targetPos = vertPos -0.25;

        setAngle(167.0);
        state = STATE.OUTSPEC;
    }
    public void inSpecTransfer(){
        setAngle(30);
        state=STATE.INSPECTRANSFER;
    }
    public void inSpec() {
//        targetPos = vertPos +0.35;
        setAngle(-7.0);
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