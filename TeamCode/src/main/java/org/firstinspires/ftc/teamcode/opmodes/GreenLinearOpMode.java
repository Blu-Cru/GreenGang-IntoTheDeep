package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;
import org.firstinspires.ftc.teamcode.subsystems.gamepad.StickyGamepad;
import org.firstinspires.ftc.teamcode.subsystems.intake.arm.IntakeArm;
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
    double y, x, rx;


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
    public void addIntakeArm() {arm = robot.addIntakeArm();}
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

    public void enableFTCDashboard() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    public void bucket (Robot robot, int h) {
        robot.intakeArm.transfer();
        robot.intakeWrist.transfer();
        robot.outtakeClaw.open();
        robot.intake.spit();
        robot.outtakeClaw.close();
        // slides lift (based on h)
        robot.clawArm.bucket();
        robot.clawWrist.bucket();
        robot.outtakeClaw.open();
        // wait 2 sec
        robot.outtakeClaw.close();
        robot.clawWrist.intake();
        robot.clawArm.intake();
        // slides drop
    }
    public void autoSpec(Robot robot){
        // arm to spec, wrist to spec & claw to spec & slides to spec & open claw > slides down, arm reset, wrist reset, claw reset
        robot.clawArm.outSpec();
        robot.clawWrist.outSpec();
        // slides to spec
        robot.outtakeClaw.open();
        // drop off piece wait 2 sec
        robot.clawArm.intake();
        robot.clawWrist.transfer();
        robot.outtakeClaw.close();
        // slides down
    }

    public void spit(IntakeColorSensor color, Intake intake, Alliance alliance){
        switch(alliance) {
            case RED:
                if (robot.color.slotState.equals(IntakeColorSensor.SlotState.BLUE)) {
                    robot.intake.spit();
                }
                break;
            case BLUE:
                if (robot.color.slotState.equals(IntakeColorSensor.SlotState.RED)) {
                    robot.intake.spit();
                }
                break;
        }
    }

    public void drive(Drive drive){
        switch(drive) {
            case FIELDCENTRIC:
                if (gamepad1.options) {
                    robot.drivetrain.setExternalHeading(Math.toRadians(90));
                }
                robot.drivetrain.fieldCentricDrive(x, y, rx);
                break;
            case ROBOTCENTRIC:
                robot.drivetrain.drive(x, y, rx);
                break;
        }
    }

    public void driveControl(){
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = -gamepad1.right_stick_x;

        if(gamepad1.right_trigger > 0.4) {
            Drivetrain.drivePower = 0.3;
        }
        else {
            Drivetrain.drivePower = 0.6;
        }
    }
}
