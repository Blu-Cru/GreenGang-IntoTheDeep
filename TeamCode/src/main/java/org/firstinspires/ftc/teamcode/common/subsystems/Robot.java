package org.firstinspires.ftc.teamcode.common.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.common.subsystems.hang.Hang;
import org.firstinspires.ftc.teamcode.common.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.common.subsystems.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.common.subsystems.intake.IntakeWrist;
import org.firstinspires.ftc.teamcode.common.subsystems.outtake.arm.ClawArm;
import org.firstinspires.ftc.teamcode.common.subsystems.outtake.wrist.ClawWrist;
import org.firstinspires.ftc.teamcode.common.subsystems.outtake.outtake.OuttakeClaw;
import org.firstinspires.ftc.teamcode.common.subsystems.slides.HorizontalSlides;
import org.firstinspires.ftc.teamcode.common.subsystems.slides.VertSlides;

import java.util.ArrayList;

public class Robot {
    private static Robot instance;
    HardwareMap hardwareMap; // reference to hardware

    // all subsystems
    public Intake intake;
    public ClawArm clawArm;
    public Drivetrain drivetrain;
    public IntakeColorSensor color;
    public ClawWrist clawWrist;
    public OuttakeClaw outtakeClaw;
    public VertSlides vs;
    public HorizontalSlides horizontalSlides;
    public Hang hang;
    public IntakeWrist wrist;

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
    public Hang addHang(){
        hang = new Hang(hardwareMap);
        subsystems.add(hang);
        return hang;
    }
    public OuttakeClaw addOuttakeClaw(){
        outtakeClaw = new OuttakeClaw(hardwareMap);
        subsystems.add(outtakeClaw);
        return outtakeClaw;
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

    public IntakeWrist addIntakeWrist() {
        wrist = new IntakeWrist(hardwareMap);
        subsystems.add(wrist);
        return wrist;
    }


    public HorizontalSlides addHorizontalSlides(){
        horizontalSlides = new HorizontalSlides(hardwareMap);
        subsystems.add(horizontalSlides);
        return horizontalSlides;
    }

    public void telemetry(Telemetry telemetry) {
        for(GreenSubsystem subsystem : subsystems) {
            subsystem.telemetry(telemetry);
        }
    }

    public void update(){
        for (GreenSubsystem subsystem : subsystems) {
            subsystem.update();
        }
    }

    // call this after every op mode
    public static void kill() {
        instance = null;
    }
}
