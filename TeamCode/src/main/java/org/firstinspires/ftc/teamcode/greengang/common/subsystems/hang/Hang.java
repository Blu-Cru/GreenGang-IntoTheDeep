package org.firstinspires.ftc.teamcode.greengang.common.subsystems.hang;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.greengang.common.util.GreenSubsystem;

public class Hang implements Subsystem, GreenSubsystem {
    public PTO pmo;
    public TiltServos ts;

    public Hang(HardwareMap hardwareMap) {
        pmo = new PTO(hardwareMap);
        ts = new TiltServos(hardwareMap);
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
}
