package org.firstinspires.ftc.teamcode.subsystems.hang;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

public class hang implements GreenSubsystem, Subsystem {

    DcMotor hangMotor;
    double manualPower;

    public hang(HardwareMap hardwareMap){
        hangMotor = hardwareMap.get(DcMotor.class, "");
    }

                @Override
    public void init() {

    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    @Override
    public void telemetry(Telemetry telemetry) {

    }

    @Override
    public void update() {

    }

    public void setManualPower(double power){
        manualPower = power;
    }
}
