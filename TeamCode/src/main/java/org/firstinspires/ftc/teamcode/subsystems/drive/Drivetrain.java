package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class Drivetrain extends SampleMecanumDrive implements Subsystem {

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
