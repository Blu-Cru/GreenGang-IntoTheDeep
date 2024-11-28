package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple.*;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class Intake implements Subsystem {
    CRServo intake, intake2;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(CRServo.class, "leftServo");
        intake2 = hardwareMap.get(CRServo.class, "rightServo");

        intake.setDirection(Direction.REVERSE);
        intake2.setDirection(Direction.FORWARD);
    }

    public void init() {
        intake.setPower(0);
        intake2.setPower(0);
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    public void in(){
        intake.setPower(1);
        intake2.setPower(1);
    }

    public void stop(){
        intake.setPower(0);
        intake2.setPower(0);
    }

    public void spit(){
        intake.setPower(-1);
        intake2.setPower(-1);
    }

    public String telemetry(Telemetry telemetry) {
        telemetry.addData("intake power", intake.getPower());
        telemetry.addData("intake2 power", intake2.getPower());
        return null;
    }

}
