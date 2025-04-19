package org.firstinspires.ftc.teamcode.common.subsystems.drive;

public enum DriveMode {
    FIELDCENTRIC,
    ROBOTCENTRIC;
    public DriveMode flip() {
        if(this == FIELDCENTRIC) {
            return ROBOTCENTRIC;
        } else {
            return FIELDCENTRIC;
        }
    }

}
