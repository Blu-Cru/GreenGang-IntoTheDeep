package org.firstinspires.ftc.teamcode.subsystems.outtake.outtake;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;

public class ClawDistanceSensor implements GreenSubsystem, Subsystem {

    Rev2mDistanceSensor distanceSensor;
    double distance;
    boolean reading;

    public ClawDistanceSensor(HardwareMap hardwareMap) {
        distanceSensor = hardwareMap.get(Rev2mDistanceSensor.class, "distance");
    }
    @Override
    public void init() {
        distanceSensor.initialize();
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("distance: ", distanceSensor.getDistance(DistanceUnit.INCH));
    }

    @Override
    public void update() {
            distance = distanceSensor.getDistance(DistanceUnit.INCH);
    }

    public enum SlotState {
        FULL,
        EMPTY;
    }



    public SlotState getSlotState() {
        if (distanceSensor.getDistance(DistanceUnit.INCH) >= 1.5) {
            return SlotState.EMPTY;
        } else {
            return SlotState.FULL;
        }
    }
}
