package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple.*;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    //public static final double launcherPos = 1;

    CRServo intake, intake2;


    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(CRServo.class, "leftServo");
        intake2 = hardwareMap.get(CRServo.class, "rightServo");

        intake.setDirection(Direction.FORWARD);
        intake2.setDirection(Direction.FORWARD);
        // set directions
        //Forward = blocks in
    }

    public void init() {
        intake.setPower(0);
    }


    public void IntakeSetPower(double power){
        intake.setPower(power);
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("intake power", intake.getPower());

    }
}
