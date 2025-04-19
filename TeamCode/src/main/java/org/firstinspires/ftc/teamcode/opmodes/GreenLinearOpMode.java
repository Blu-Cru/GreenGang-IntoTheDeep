package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.subsystems.Alliance;
import org.firstinspires.ftc.teamcode.common.subsystems.drive.DriveMode;
import org.firstinspires.ftc.teamcode.common.subsystems.gamepad.StickyGamepad;
import org.firstinspires.ftc.teamcode.common.subsystems.hang.Hang;
import org.firstinspires.ftc.teamcode.common.subsystems.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.common.subsystems.Robot;
import org.firstinspires.ftc.teamcode.common.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.common.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.common.subsystems.intake.IntakeWrist;
import org.firstinspires.ftc.teamcode.common.subsystems.outtake.arm.ClawArm;
import org.firstinspires.ftc.teamcode.common.subsystems.outtake.wrist.ClawWrist;
import org.firstinspires.ftc.teamcode.common.subsystems.outtake.outtake.OuttakeClaw;
import org.firstinspires.ftc.teamcode.common.subsystems.slides.HorizontalSlides;
import org.firstinspires.ftc.teamcode.common.subsystems.slides.VertSlides;

public class GreenLinearOpMode extends LinearOpMode {

    public Robot robot;
    public VertSlides vs;
    public Drivetrain drivetrain;
    public ClawArm transfer;
    public ClawWrist clawWrist;
    public IntakeWrist wrist;
    public OuttakeClaw outtakeClaw;
    public Intake intake;
    public IntakeColorSensor intakeColorSensor;
    public StickyGamepad stickyG1;
    public HorizontalSlides horizontalSlides;
    public Hang hang;
    public StickyGamepad stickyG2;
    public Alliance alliance;
    public DriveMode driveMode;


    @Override
    public final void runOpMode() throws InterruptedException {
        CommandScheduler.getInstance().cancelAll();

        stickyG1 = new StickyGamepad(gamepad1);
        stickyG2 = new StickyGamepad(gamepad2);
        alliance = Alliance.BLUE;
        driveMode = DriveMode.FIELDCENTRIC;

        robot = Robot.getInstance();
        robot.create(hardwareMap);
        initialize();
        robot.init();

        while(opModeInInit()) {
            if(stickyG1.x) {
                alliance = alliance.flip();
            }
            if (stickyG1.b){
                driveMode = driveMode.flip();
            }

            stickyG1.update();
            stickyG2.update();

            // safety for switching controllers
            if(gamepad1.start || gamepad2.start) {
                continue;
            }

            initLoop();
            CommandScheduler.getInstance().run();

            // telemetry
            telemetry.addData("ALLIANCE: ", alliance);
            telemetry.addData("DRIVE: ", driveMode);
            telemetry.update();
        }


        waitForStart();
        onStart();

        while (opModeIsActive()) {
            stickyG1.update();
            stickyG2.update();

            // safety for switching controllers
//            if(!(gamepad1.start || gamepad2.start)) {
            periodic();
//            }

            CommandScheduler.getInstance().run();

            robot.update();

            telemetry();
            robot.telemetry(telemetry);
            telemetry.update();
        }

        end();
        Robot.kill();

    }

    public void enableFTCDashboard() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    // methods to be over written
    public void initialize() {}
    public void initLoop() {
    }
    public void onStart() {}
    public void periodic() {}
    public void telemetry() {}
    public void end() {}

    // subsystems
    public void addIntakeWrist() {wrist = robot.addIntakeWrist();}
    public void addClawArm() {transfer = robot.addTransfer();}
    public void addVertSlides() {vs = robot.addVertSlides();}
    public void addClawWrist() {clawWrist = robot.addClawWrist();}
    public void addOuttakeClaw() {outtakeClaw = robot.addOuttakeClaw();}
    public void addDrivetrain() {drivetrain = robot.addDrivetrain();}
    public void addIntake() {intake = robot.addIntake();}
    public void addHang() {hang = robot.addHang();}
    public void addIntakeColorSensor() {intakeColorSensor = robot.addIntakeColorSensor();}
    public void addHorizontalSlides() {horizontalSlides = robot.addHorizontalSlides();}
    public void addStickyG1() {stickyG1 = new StickyGamepad(gamepad1);}
    public void addStickyG2() {stickyG2 = new StickyGamepad(gamepad2);}

}
