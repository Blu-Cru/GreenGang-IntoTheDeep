package org.firstinspires.ftc.teamcode.subsystems;

public enum Alliance {
    RED,
    BLUE;

    // flip the alliance color
    public Alliance flip() {
        if(this == RED) {
            return BLUE;
        } else {
            return RED;
        }
    }

}
