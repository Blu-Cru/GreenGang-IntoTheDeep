package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class ClawArm implements Subsystem {
    public Servo clawArm;
    private ServoControllerEx clawArmController;
    public ClawArm(HardwareMap hardwareMap) {
        clawArm = hardwareMap.get(Servo.class, "claw arm");
    }

    public void init() {
        clawArmController = (ServoControllerEx) clawArm.getController();
        //claw.setPosition(1);
    }

    public void intake(){
        clawArm.setPosition(0.45);//fix
    }
    public void transfer() {
        clawArm.setPosition(0.8);//fix
    }
    public void clawWristSetPos(double pos){
        clawArmController.setServoPwmEnable(clawArm.getPortNumber());
        clawArm.setPosition(pos);
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("claw arm position: ", clawArm.getPosition());
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

}
