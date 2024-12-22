package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface GreenSubsystem {

    void init();
    void read();
    void write();
    String telemetry(Telemetry telemetry);
}