package org.firstinspires.ftc.teamcode.greengang.common.util;

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
