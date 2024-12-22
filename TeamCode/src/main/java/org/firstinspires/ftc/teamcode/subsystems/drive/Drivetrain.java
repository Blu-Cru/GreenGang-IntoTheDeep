package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

public class Drivetrain extends SampleMecanumDrive implements GreenSubsystem, Subsystem {

    public static double drivePower;
    public Drivetrain (HardwareMap hardwareMap) {
        super(hardwareMap);
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

    @Override
    public void init() {

    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    public String telemetry(Telemetry tele) {
        tele.addData("heading ", getExternalHeading());
        return null;
    }
}
