package org.firstinspires.ftc.teamcode.common.subsystems.hang;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.subsystems.GreenSubsystem;

public class Hang implements GreenSubsystem, Subsystem {

    DcMotor hangMotor;
    double manualPower;

    public Hang(HardwareMap hardwareMap){
        hangMotor = hardwareMap.get(DcMotor.class, "hang");
    }

    @Override
    public void init() {

    }

    @Override
    public void telemetry(Telemetry telemetry) {

    }

    @Override
    public void update() {

    }
    public void setHangPower(double power){
        hangMotor.setPower(power);
    }
}
