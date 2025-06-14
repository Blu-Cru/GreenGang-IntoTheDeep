package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.hang.Hang;
import org.firstinspires.ftc.teamcode.subsystems.outtake.outtake.ClawDistanceSensor;
import org.firstinspires.ftc.teamcode.subsystems.outtake.wrist.Turret;
import org.firstinspires.ftc.teamcode.subsystems.util.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;
import org.firstinspires.ftc.teamcode.subsystems.gamepad.StickyGamepad;
import org.firstinspires.ftc.teamcode.subsystems.hang.PTO;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.util.Globals;
import org.firstinspires.ftc.teamcode.subsystems.util.Robot;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeWrist;
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
    public IntakeWrist wrist;
    public OuttakeClaw outtakeClaw;
    public Intake intake;
    public Hang hang;
    public Turret turret;
    public IntakeColorSensor intakeColorSensor;
    public StickyGamepad stickyG1;
    public HorizontalSlides horizontalSlides;
    public StickyGamepad stickyG2;
    public Alliance alliance;
    public Drive drive;
    public ClawDistanceSensor distanceSensor;
//    public PinPointLocalizer ppl;

    @Override
    public final void runOpMode() throws InterruptedException {
        CommandScheduler.getInstance().cancelAll();

        stickyG1 = new StickyGamepad(gamepad1);
        stickyG2 = new StickyGamepad(gamepad2);

        robot = Robot.getInstance();
        robot.create(hardwareMap);
        initialize();
        robot.init();

        while(opModeInInit()) {
            if(stickyG1.x) {
                Globals.alliance = Globals.alliance.flip();
            }
            if (stickyG1.b){
                Globals.fieldCentric = !Globals.fieldCentric;
            }

            stickyG1.update();
            stickyG2.update();

            // safety for switching controllers
            if(gamepad1.start || gamepad2.start) {
                continue;
            }

            CommandScheduler.getInstance().run();

            // telemetry
            telemetry.addData("ALLIANCE: ", Globals.alliance);
            telemetry.addData("FIELD CENTRIC MODE: ", Globals.fieldCentric);
            telemetry.update();
        }


        waitForStart();
        onStart();

        while (opModeIsActive()) {
            stickyG1.update();
            stickyG2.update();

            periodic();
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
    public void initLoop() {}
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
    public void addIntakeColorSensor() {intakeColorSensor = robot.addIntakeColorSensor();}
    public void addHorizontalSlides() {horizontalSlides = robot.addHorizontalSlides();}
    public void addClawDistance() {distanceSensor = robot.addClawDistanceSensor();}
    public void addStickyG1() {stickyG1 = new StickyGamepad(gamepad1);}
    public void addStickyG2() {stickyG2 = new StickyGamepad(gamepad2);}
    public void addHang() {hang = robot.addHang();}
    public void addTurret(){turret = robot.addTurret();}
}
