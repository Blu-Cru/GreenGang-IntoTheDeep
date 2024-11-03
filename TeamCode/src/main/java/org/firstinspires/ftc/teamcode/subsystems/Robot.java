package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.transfer.Transfer;

import java.util.ArrayList;

public class Robot {
    private static Robot instance;
    HardwareMap hardwareMap; // reference to hardware

    // all subsystems
    public Intake intake;
    public Transfer transfer;
    public Drivetrain drivetrain;
    public IntakeColorSensor color;

    public Alliance alliance;

    // list of all subsystems
    ArrayList<Subsystem> subsystems;

    public static Robot getInstance() {
        if(instance == null) {
            instance = new Robot();
        }
        return instance;
    }

    private Robot(){
        subsystems = new ArrayList<>();
    }

    public void create(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    // initializes subsystems
    public void init() {
        for(Subsystem subsystem : subsystems) {
            subsystem.init();
        }
    }

    public void read() {
        for(Subsystem subsystem : subsystems) {
            subsystem.read();
        }
    }

    public void write() {
        for(Subsystem subsystem : subsystems) {
            subsystem.write();
        }
    }

    public Intake addIntake() {
        intake = new Intake(hardwareMap);
        subsystems.add(intake);
        return intake;
    }

    public IntakeColorSensor addIntakeColorSensor() {
        color = new IntakeColorSensor(hardwareMap);
        subsystems.add(color);
        return color;
    }

    public Drivetrain addDrivetrain() {
        drivetrain = new Drivetrain(hardwareMap);
        subsystems.add(drivetrain);
        return drivetrain;
    }

    public Transfer addTransfer() {
        transfer = new Transfer(hardwareMap);
        subsystems.add(transfer);
        return transfer;
    }

//    public void telemetry(Telemetry telemetry) {
//        for(Subsystem subsystem : subsystems) {
//            subsystem.telemetry(telemetry);
//        }
//    }

    // call this after every op mode
    public static void kill() {
        instance = null;
    }
}
