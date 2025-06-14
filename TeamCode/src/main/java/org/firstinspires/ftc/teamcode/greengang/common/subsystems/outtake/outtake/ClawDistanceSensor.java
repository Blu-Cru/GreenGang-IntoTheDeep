package org.firstinspires.ftc.teamcode.greengang.common.subsystems.outtake.outtake;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.greengang.common.util.GreenSubsystem;

public class ClawDistanceSensor implements GreenSubsystem, Subsystem {

    Rev2mDistanceSensor distanceSensor;
    double distance;
    public enum SlotState {
        FULL,
        EMPTY;
    }
    SlotState state;
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
        telemetry.addData("state: ", state);
    }

    @Override
    public void update() {
        distance = distanceSensor.getDistance(DistanceUnit.INCH);
        if(distance < 1.5){
            state=SlotState.FULL;
        } else {
            state=SlotState.EMPTY;
        }
    }
    public boolean isFull() {
       return state == SlotState.FULL;
    }
}
