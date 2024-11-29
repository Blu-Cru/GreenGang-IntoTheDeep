package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.arm.Arm;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.intake.wrist.IntakeWrist;
import org.firstinspires.ftc.teamcode.subsystems.outtake.arm.ClawArm;
import org.firstinspires.ftc.teamcode.subsystems.outtake.wrist.ClawWrist;
import org.firstinspires.ftc.teamcode.subsystems.outtake.outtake.OuttakeClaw;

import java.util.ArrayList;

public class Robot {
    private static Robot instance;
    HardwareMap hardwareMap; // reference to hardware

    // all subsystems
    public Intake intake;
    public IntakeWrist intakeWrist;
    public ClawArm transfer;
    public Drivetrain drivetrain;
    public IntakeColorSensor color;
    public IntakeArm arm;
    public ClawWrist clawWrist;
    public OuttakeClaw outtakeClaw;
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

    public Robot create(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        return this;
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

    public ClawWrist addClawWrist(){
        clawWrist = new ClawWrist(hardwareMap);
        subsystems.add(clawWrist);
        return clawWrist;
    }

    public OuttakeClaw addOuttakeClaw(){
        outtakeClaw = new OuttakeClaw(hardwareMap);
        subsystems.add(outtakeClaw);
        return outtakeClaw;
    }

    public IntakeWrist addIntakeWrist()
    {
        intakeWrist =new IntakeWrist(hardwareMap);
        subsystems.add(intakeWrist);
        return intakeWrist;
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

    public Arm addArm() {
        arm = new Arm(hardwareMap);
        subsystems.add(arm);
        return arm;
    }

    public ClawArm addTransfer() {
        transfer = new ClawArm(hardwareMap);
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
