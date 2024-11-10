package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple.*;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class Intake implements Subsystem {
    CRServo intake, intake2, wrist;

    // wrist transfer servo .8
    // intake .45
    // rest
    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(CRServo.class, "leftServo");
        intake2 = hardwareMap.get(CRServo.class, "rightServo");
        //wrist = hardwareMap.get(CRServo.class, "wrist");

        intake.setDirection(Direction.REVERSE);
        intake2.setDirection(Direction.FORWARD);
        // wrist.setDirection(Direction.FORWARD); // might need to be updated
        // set direction: Forward = blocks in
    }

    public void init() {
        intake.setPower(0);
        intake2.setPower(0);
       // wrist.setPower(0); // could be wrong
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    public void intakeSetPower(double power) {
        intake.setPower(power);
        intake2.setPower(power);
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("intake power", intake.getPower());
        telemetry.addData("intake2 power", intake2.getPower());
    }

//    public void transfer() {
//        wrist.setPower(0);
//    }

}
