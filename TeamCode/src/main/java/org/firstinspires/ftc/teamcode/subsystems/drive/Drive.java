package org.firstinspires.ftc.teamcode.subsystems.drive;

public enum Drive {
    FIELDCENTRIC,
    ROBOTCENTRIC;
    public Drive flip() {
        if(this == FIELDCENTRIC) {
            return ROBOTCENTRIC;
        } else {
            return FIELDCENTRIC;
        }
    }

}
