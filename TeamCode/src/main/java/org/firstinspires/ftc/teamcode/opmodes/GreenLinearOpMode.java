package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.arm.Arm;
import org.firstinspires.ftc.teamcode.subsystems.gamepad.StickyGamepad;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.wrist.IntakeWrist;
import org.firstinspires.ftc.teamcode.subsystems.outtake.arm.ClawArm;
import org.firstinspires.ftc.teamcode.subsystems.outtake.wrist.ClawWrist;
import org.firstinspires.ftc.teamcode.subsystems.outtake.outtake.OuttakeClaw;

public class GreenLinearOpMode extends LinearOpMode {

    public Robot robot;
    public Drivetrain drivetrain;
    public ClawArm transfer;
    public ClawWrist clawWrist;
    public OuttakeClaw outtakeClaw;

    public Intake intake;
    public IntakeWrist intakeWrist;
    public IntakeColorSensor intakeColorSensor;
    public IntakeArm arm;
    public StickyGamepad stickyG1;
    public StickyGamepad stickyG2;


    // methods to be overriden
    public void initialize() {}
    public void initLoop() {}
    public void onStart() {}
    public void periodic() {}
    public void telemetry() {}
    public void end() {}

    public void addClawArm() {transfer = robot.addTransfer();}
    public void addClawWrist() {clawWrist = robot.addClawWrist();}
    public void addOuttakeClaw() {outtakeClaw = robot.addOuttakeClaw();}
    public void addDrivetrain() {drivetrain = robot.addDrivetrain();}
    public void addIntake() {intake = robot.addIntake();}
    public void addIntakeWrist()
    {
        intakeWrist = robot.addIntakeWrist();
    }
    public void addArm() {arm = robot.addArm();}
    public void addIntakeColorSensor() {intakeColorSensor = robot.addIntakeColorSensor();}
    public void addStickyG1() {stickyG1 = new StickyGamepad(gamepad1);}
    public void addStickyG2() {stickyG2 = new StickyGamepad(gamepad2);}

    @Override
    public void runOpMode() throws InterruptedException {
        while (opModeInInit()){
            stickyG1.update();
            stickyG2.update();
        }
    }
}
