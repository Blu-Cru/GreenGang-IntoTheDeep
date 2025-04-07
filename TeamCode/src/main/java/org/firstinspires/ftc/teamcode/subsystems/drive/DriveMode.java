package org.firstinspires.ftc.teamcode.subsystems.drive;

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
