package org.firstinspires.ftc.teamcode.subsystems.hang;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hang {
    public PTO pmo;
    public TiltServos ts;

    public Hang(HardwareMap hardwareMap) {
        pmo = new PTO(hardwareMap);
        ts = new TiltServos(hardwareMap);
    }
}
