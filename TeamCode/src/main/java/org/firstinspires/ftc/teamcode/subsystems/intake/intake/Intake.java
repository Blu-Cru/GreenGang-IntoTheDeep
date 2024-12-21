package org.firstinspires.ftc.teamcode.subsystems.intake.intake;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple.*;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

public class Intake implements GreenSubsystem, Subsystem {
    CRServo intake, intake2;

    public enum STATE {
        IN,
        REST,
        SPIT;
    }

    public STATE state;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(CRServo.class, "leftServo");
        intake2 = hardwareMap.get(CRServo.class, "rightServo");

        intake.setDirection(Direction.REVERSE);
        intake2.setDirection(Direction.FORWARD);
        state = STATE.REST;
    }

    public void init() {
        intake.setPower(0);
        intake2.setPower(0);
        state = STATE.REST;
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
        state = STATE.IN;
    }

    public void stop(){
        intake.setPower(0);
        intake2.setPower(0);
        state = STATE.REST;
    }

    public void spit(){
        intake.setPower(-1);
        intake2.setPower(-1);
        state = STATE.SPIT;
    }

    public String telemetry(Telemetry telemetry) {
        telemetry.addData("INTK POW ", intake.getPower());
        telemetry.addData("INTK STATE ", state);
        return null;
    }

}
