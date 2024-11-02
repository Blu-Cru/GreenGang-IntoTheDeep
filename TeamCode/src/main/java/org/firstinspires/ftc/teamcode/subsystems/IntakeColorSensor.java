package org.firstinspires.ftc.teamcode.subsystems;
// package org.firstinspires.ftc.teamcode.blucru.common.subsystems.intake;

import android.graphics.Color;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class IntakeColorSensor {

    public enum SlotState {
        EMPTY,
        RED,
        YELLOW,
        BLUE,
        FULL
    }

    static double[]
            FRONT_DISTANCE_RANGE = {0.05, 0.65}; // inches


    static double HUE_GREEN_HIGH_YELLOW_LOW = 270,
            HUE_PURPLE_HIGH_GREEN_LOW = 150,
            SATURATION_WHITE_HIGH = 0.3;


    public SlotState frontSlotState;
    RevColorSensorV3 frontSensor;
    NormalizedRGBA frontRGBA;
    double frontR;
    double frontG;
    double frontB;
    float[] frontHSV;
    double frontHue;
    double frontDistance;
    double frontLightDetected;
    double timeLastEmpty;
    boolean reading;

    public IntakeColorSensor(HardwareMap hardwareMap) {
        frontSensor = hardwareMap.get(RevColorSensorV3.class, "color");
        frontSlotState = SlotState.EMPTY;

        frontHSV = new float[3];

        reading = false;
    }

    public void init() {
        frontRGBA = frontSensor.getNormalizedColors();
        frontDistance = frontSensor.getDistance(DistanceUnit.INCH);
        frontLightDetected = frontSensor.getLightDetected();
    }

    private boolean inRange(double value, double[] range) {
        return value >= range[0] && value <= range[1];
    }

    public void reset() {
        frontSlotState = SlotState.EMPTY;
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
        return frontSlotState == SlotState.EMPTY;
    }

    public boolean hasOne() {
        return frontSlotState != SlotState.EMPTY;
    }

    public void read() {
        if (reading) {
            frontDistance = frontSensor.getDistance(DistanceUnit.INCH);

            frontRGBA = frontSensor.getNormalizedColors();

            frontR = frontRGBA.red;
            frontG = frontRGBA.green;
            frontB = frontRGBA.blue;
            frontHSV = getHSV(frontRGBA); // sets frontHSV
            frontHue = frontHSV[0];

            frontSlotState = getSlotState(frontDistance, frontHSV);
        }
    }

    public SlotState getSlotState(double distance, float[] hsv) {
        if (frontDistance >= 1) {
            return SlotState.EMPTY;
        } else {
            if (frontG > frontR && frontG > frontB) {
                return SlotState.YELLOW;
            } else if (frontB > frontR) {
                return SlotState.BLUE;
            } else if (frontR > frontB){
                return SlotState.RED;
            } else {
                return SlotState.FULL;
            }
        }
    }


        // scales the front RGB values to be between 0 and 255
        public void scaleFrontRGB () {
            double max = Math.max(Math.max(frontR, frontB), frontG);
            frontR = frontR / max;
            frontG = frontG / max;
            frontB = frontB / max;
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
            return frontSlotState != SlotState.EMPTY;
        }

        public void testTelemetry (Telemetry telemetry){
            telemetry.addData("front R", frontR);
            telemetry.addData("front G", frontG);
            telemetry.addData("front B", frontB);
            telemetry.addData("front H:: ", frontHSV[0]);
            telemetry.addData("front S:: ", frontHSV[1]);
            telemetry.addData("front V:: ", frontHSV[2]);
            telemetry.addData("FRONT SLOT STATE: ", frontSlotState);
            telemetry.addData("front distance", frontDistance);
        }
    }

