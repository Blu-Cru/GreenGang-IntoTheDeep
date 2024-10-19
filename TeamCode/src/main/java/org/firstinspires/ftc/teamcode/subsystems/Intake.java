package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    //public static final double launcherPos = 1;

    private CRServo intake;
    public static final double intakeIn = 1;
    public static final double intakeOut = -1;


    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(CRServo.class, "intake");
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
