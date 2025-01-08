package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface GreenSubsystem {

    void init();
    void read();
    void write();
    void telemetry(Telemetry telemetry);
    void update();
}
