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
import org.firstinspires.ftc.teamcode.subsystems.slides.HorizontalSlides;
import org.firstinspires.ftc.teamcode.subsystems.slides.VertSlides;

public class GreenLinearOpMode extends LinearOpMode {

    public Robot robot;
    public VertSlides vs;
    public Drivetrain drivetrain;
    public ClawArm transfer;
    public ClawWrist clawWrist;
    public OuttakeClaw outtakeClaw;

    public Intake intake;
    public IntakeWrist intakeWrist;
    public IntakeColorSensor intakeColorSensor;
    public IntakeArm arm;
    public StickyGamepad stickyG1;
    public HorizontalSlides horizontalSlides;
    public StickyGamepad stickyG2;

    @Override
    public void runOpMode() throws InterruptedException {

        stickyG1 = new StickyGamepad(gamepad1);
        stickyG2 = new StickyGamepad(gamepad2);

        robot = Robot.getInstance();
        robot.create(hardwareMap);

        initialize();
        robot.init();

        while (opModeInInit()) {
            stickyG1.update();
            stickyG2.update();

            if (gamepad1.start || gamepad2.start) {
                continue;
            }

            initLoop();
            telemetry();
            telemetry.update();
        }

        waitForStart();
        robot.read();
        onStart();

        while (!isStopRequested() && opModeIsActive()) {
            stickyG1.update();
            stickyG2.update();

            robot.read();

            if (gamepad1.start || gamepad2.start) {
                continue;
            }

            periodic();
            robot.write();

            robot.telemetry(telemetry);
            telemetry();
            telemetry.update();
        }

        end();
        Robot.kill();

    }

    public void enableFTCDashboard() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    // methods to be overriden
    public void initialize() {}
    public void initLoop() {}
    public void onStart() {}
    public void periodic() {}
    public void telemetry() {}
    public void end() {}

    // subsystems
    public void addClawArm() {transfer = robot.addTransfer();}
    public void addVertSlides() {vs = robot.addVertSlides();}
    public void addClawWrist() {clawWrist = robot.addClawWrist();}
    public void addOuttakeClaw() {outtakeClaw = robot.addOuttakeClaw();}
    public void addDrivetrain() {drivetrain = robot.addDrivetrain();}
    public void addIntake() {intake = robot.addIntake();}
    public void addIntakeWrist() { intakeWrist = robot.addIntakeWrist();}
    public void addIntakeArm() {arm = robot.addIntakeArm();}
    public void addIntakeColorSensor() {intakeColorSensor = robot.addIntakeColorSensor();}
    public void addHorizontalSlides() {horizontalSlides = robot.addHorizontalSlides();}
    public void addStickyG1() {stickyG1 = new StickyGamepad(gamepad1);}
    public void addStickyG2() {stickyG2 = new StickyGamepad(gamepad2);}

}
