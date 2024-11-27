package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class ClawWrist implements Subsystem {
    Servo clawWrist;
    private ServoControllerEx clawWristController;

    public ClawWrist(HardwareMap hardwareMap) {
        clawWrist = hardwareMap.get(Servo.class, "claw wrist");
    }

    public void init() {
        clawWristController = (ServoControllerEx) clawWrist.getController();
        //clawWrist.setPosition(1);
    }

    public void intake(){
        clawWrist.setPosition(0.448);//0.448
    }
    public void transfer() {
        clawWrist.setPosition(1);
    }
    public void clawWristSetPos(double pos){
        clawWristController.setServoPwmEnable(clawWrist.getPortNumber());
        clawWrist.setPosition(pos);
    }

    public String telemetry(Telemetry telemetry){
        telemetry.addData("claw wrist power", clawWrist.getPosition());
        return null;
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }
}
