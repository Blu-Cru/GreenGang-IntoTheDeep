package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.intake.arm.IntakeArm;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.intake.wrist.IntakeWrist;
import org.firstinspires.ftc.teamcode.subsystems.outtake.arm.ClawArm;
import org.firstinspires.ftc.teamcode.subsystems.outtake.wrist.ClawWrist;
import org.firstinspires.ftc.teamcode.subsystems.outtake.outtake.OuttakeClaw;
import org.firstinspires.ftc.teamcode.subsystems.slides.VertSlides;

import java.util.ArrayList;

public class Robot {
    private static Robot instance;
    HardwareMap hardwareMap; // reference to hardware

    // all subsystems
    public Intake intake;
    public IntakeWrist intakeWrist;
    public ClawArm clawArm;
    public Drivetrain drivetrain;
    public IntakeColorSensor color;
    public IntakeArm intakeArm;
    public ClawWrist clawWrist;
    public OuttakeClaw outtakeClaw;
    public VertSlides vs;
    ArrayList<GreenSubsystem> subsystems;

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
        for(GreenSubsystem subsystem : subsystems) {
            subsystem.init();
        }
    }

    public void read() {
        for(GreenSubsystem subsystem : subsystems) {
            subsystem.read();
        }
    }

    public void write() {
        for(GreenSubsystem subsystem : subsystems) {
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

    public IntakeArm addIntakeArm() {
        intakeArm = new IntakeArm(hardwareMap);
        subsystems.add(intakeArm);
        return intakeArm;
    }

    public ClawArm addTransfer() {
        clawArm = new ClawArm(hardwareMap);
        subsystems.add(clawArm);
        return clawArm;
    }

    public VertSlides addVertSlides() {
        vs = new VertSlides(hardwareMap);
        subsystems.add(vs);
        return vs;
    }

    public void telemetry(Telemetry telemetry) {
        for(GreenSubsystem subsystem : subsystems) {
            subsystem.telemetry(telemetry);
        }
    }

    // call this after every op mode
    public static void kill() {
        instance = null;
    }
}
