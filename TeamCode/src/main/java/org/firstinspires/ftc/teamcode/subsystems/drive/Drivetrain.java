package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.util.Globals;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;

public class Drivetrain extends SampleMecanumDrive implements GreenSubsystem, Subsystem {

    public double drivePower;
    public DrivePID pid;
    public double heading;
    public Drivetrain (HardwareMap hardwareMap) {
        super(hardwareMap);
        pid = new DrivePID();
        drivePower = 1; // used to be .5
    }

    public void drive(double x, double y, double rotate) {
        Vector2d input = new Vector2d(x,y);
        Vector2d output = input.rotated(Math.toRadians(-90));

        x = output.getX();
        y = output.getY();

        setWeightedDrivePower(new Pose2d(x * drivePower, y * drivePower, rotate * drivePower));
    }

    public void fieldCentricDrive(double x, double y, double rotate){
        double botHeading = getExternalHeading();

        Vector2d input = new Vector2d(x,y);
        Vector2d output;

        output = input.rotated(-botHeading);

        x = output.getX();
        y = output.getY();

        setWeightedDrivePower(new Pose2d(x * drivePower, y * drivePower, rotate * drivePower));
    }

    public void driveToHeading(double x, double y, double targetHeading) {
        pid.setTargetHeading(targetHeading);
        driveToHeading(x,y);
    }
    public void updatePID() {
        pid.headingController.setPID(DrivePID.kPHeading, DrivePID.kIHeading, DrivePID.kDHeading);
    }
    public void driveToHeading(double x, double y) {
        if (Globals.fieldCentric) {
            fieldCentricDrive(x,y, Range.clip(pid.getRotate(heading), -drivePower, drivePower));
        } else {
            drive(x,y, Range.clip(pid.getRotate(heading), -drivePower, drivePower));
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void telemetry(Telemetry tele) {
        tele.addData("heading current pos ", heading);
        tele.addData("rotate pow ", pid.getRotate(heading));
        tele.addData("drive train power ", drivePower);
    }

    @Override
    public void update() {
        updatePID();
        heading = getRawExternalHeading();
    }
}
