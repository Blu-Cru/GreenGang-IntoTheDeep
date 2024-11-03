package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;

public class GreenLinearOpMode extends LinearOpMode {

    public Robot robot;
    public Drivetrain drivetrain;
    public Transfer transfer;
    public Intake intake;
    public IntakeColorSensor intakeColorSensor;

    // methods to be overriden
    public void initialize() {}
    public void initLoop() {}
    public void onStart() {}
    public void periodic() {}
    public void telemetry() {}
    public void end() {}

    public void addDrivetrain() {drivetrain = robot.addDrivetrain();}

    public void addTransfer() {transfer = robot.addTransfer();}

    public void addIntake() {intake = robot.addIntake();}

    public void addIntakeColorSensor() {intakeColorSensor = robot.addIntakeColorSensor();}
    @Override
    public void runOpMode() throws InterruptedException {

    }
}
