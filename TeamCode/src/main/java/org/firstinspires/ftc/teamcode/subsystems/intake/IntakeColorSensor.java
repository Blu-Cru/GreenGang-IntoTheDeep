package org.firstinspires.ftc.teamcode.subsystems.intake;
// package org.firstinspires.ftc.teamcode.blucru.common.subsystems.intake;

import android.graphics.Color;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class IntakeColorSensor implements Subsystem {

    public enum SlotState {
        EMPTY,
        RED,
        YELLOW,
        BLUE,
        FULL
    }

    public SlotState slotState;
    RevColorSensorV3 sensor;
    NormalizedRGBA rgba;
    double r;
    double g;
    double b;
    float[] hsv;
    double hue;
    double distance;
    double lightDetected;
    double timeLastEmpty;
    boolean reading;

    public IntakeColorSensor(HardwareMap hardwareMap) {
        sensor = hardwareMap.get(RevColorSensorV3.class, "color");
        slotState = SlotState.EMPTY;

        hsv = new float[3];

        reading = false;
    }

    public void init() {
        rgba = sensor.getNormalizedColors();
        distance = sensor.getDistance(DistanceUnit.INCH);
        lightDetected = sensor.getLightDetected();
    }

    private boolean inRange(double value, double[] range) {
        return value >= range[0] && value <= range[1];
    }

    public void reset() {
        slotState = SlotState.EMPTY;
    }

    public float[] getHSV(NormalizedRGBA color) {
        float[] hsv = new float[3];
        int colorInt = Color.argb((int) (color.alpha * 255), (int) (color.red * 255), (int) (color.green * 255), (int) (color.blue * 255));
        Color.colorToHSV(colorInt, hsv);
//        Color.RGBToHSV((int)(color.red * 255), (int)(color.green * 255), (int)(color.blue * 255), hsv);
        return hsv;
    }

    public void write() {

    }

    public boolean empty() {
        return slotState == SlotState.EMPTY;
    }

    public boolean hasOne() {
        return slotState != SlotState.EMPTY;
    }

    public void read() {
        if (reading) {
            distance = sensor.getDistance(DistanceUnit.INCH);

            rgba = sensor.getNormalizedColors();

            r = rgba.red;
            g = rgba.green;
            b = rgba.blue;
            hsv = getHSV(rgba); // sets frontHSV
            hue = hsv[0];

            slotState = getSlotState();
        }
    }

    public SlotState getSlotState() {
        if (distance >= 1.5) {
            return SlotState.EMPTY;
        } else {
            if (g > r && g > b) {
                return SlotState.YELLOW;
            } else if (b > r) {
                return SlotState.BLUE;
            } else if (r > b){
                return SlotState.RED;
            } else {
                return SlotState.FULL;
            }
        }
    }


        // scales the front RGB values to be between 0 and 255
        public void scaleFrontRGB () {
            double max = Math.max(Math.max(r, b), g);
            r = r / max;
            g = g / max;
            b = b / max;
        }

        public void startReading () {
            reading = true;
        }

        public void stopReading () {
            reading = false;
        }

        public boolean isReading () {
            return reading;
        }

        public boolean isFull () {
            return slotState != SlotState.EMPTY;
        }

        public String telemetry (Telemetry telemetry){
            telemetry.addData("R", r);
            telemetry.addData("G", g);
            telemetry.addData("front B", b);
            telemetry.addData("slot state: ", slotState);
            telemetry.addData("distance", distance);
            return null;
        }
    }

