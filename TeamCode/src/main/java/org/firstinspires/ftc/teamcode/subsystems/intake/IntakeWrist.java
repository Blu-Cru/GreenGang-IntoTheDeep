package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class IntakeWrist implements Subsystem {

    CRServo wrist;

    private IntakeWrist(HardwareMap hardwareMap) {
        wrist = hardwareMap.get(CRServo.class, "wrist");
        wrist.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void init() {
        wrist.setPower(0); // could be wrong
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    public void transfer() {
        wrist.setPower(0.8);
    }

    public void intake(){
        wrist.setPower(0.45);
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("wrist power", wrist.getPower());
    }
}
