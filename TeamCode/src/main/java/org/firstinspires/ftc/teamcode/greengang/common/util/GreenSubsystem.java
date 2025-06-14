package org.firstinspires.ftc.teamcode.greengang.common.util;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface GreenSubsystem {
    void init();
    void telemetry(Telemetry telemetry);
    void update();
}
