package org.firstinspires.ftc.teamcode.greengang.common.util;

import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.util.Range;

public class GreenServo extends ServoImpl {

    String name;
    ServoController servoController;
    double pos=0, lastPos=0;

    public GreenServo(String name, Direction dir){
        this(Globals.hwMap.get(ServoImpl.class, name), name, dir);
    }

    public GreenServo(String name) {
        this(name, Direction.FORWARD);
    }

    public GreenServo(String name, boolean reversed) {
        this(name, reversed ? Direction.REVERSE : Direction.FORWARD);
    }

    private GreenServo(ServoImpl servo, String name, Direction direction) {
        super(servo.getController(), servo.getPortNumber(), servo.getDirection());
        this.setDirection(direction);
        this.controller = servo.getController();
        this.name = name;
    }

    public void setPosition(double position) {
        pos = Range.clip(position, 0, 1);
    }

    public double getPosition() {
        return pos;
    }

    /*
     * Enable/disable the servo
     *
     * WATCH OOUT!!!!, each servo controller is connected to 2 servo ports,
     * so using enable or disable might affect other servos
     */
    public void enable() {
        controller.pwmEnable();
    }

    public void disable() {
        controller.pwmDisable();
    }

    public void init() {
    }

    public void read() {

    }

    public void write() {
        if(Math.abs(pos - lastPos) > 0.002) {
            lastPos = pos;
            super.setPosition(pos);
        }
    }

    public String getName() {
        return name;
    }

    public void telemetry() {
        try {
            Globals.tele.addLine(name + " pos: " + pos);
        } catch (Exception ignored) {}
    }

    public void addLine(String str) {
        try {
            Globals.tele.addLine(name + " " + str);
        } catch (Exception ignored) {}
    }
}
