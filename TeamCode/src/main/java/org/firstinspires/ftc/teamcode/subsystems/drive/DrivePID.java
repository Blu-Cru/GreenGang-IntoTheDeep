package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

@Config
public class DrivePID implements GreenSubsystem {
    public static double
            kPX = 8, kIX = 0, kDX = 0, // todo: implement this & kY
            kPY = 8, kIY = 0, kDY = 0,
            kPHeading = 1, kIHeading = 0, kDHeading = 0.16; // tuned

    public PIDController xController, yController, headingController;
    public DrivePID() {
        xController = new PIDController(kPX, kIX, kDX);
        yController = new PIDController(kPY, kIY, kDY);
        headingController = new PIDController(kPHeading, kIHeading, kDHeading);
        headingController.setSetPoint(0);
    }
    public Vector2d calculate(Vector2d currentPosition) {
        double xPower = xController.calculate(currentPosition.getX());
        double yPower = yController.calculate(currentPosition.getY());
        return new Vector2d(xPower, yPower);
    }

//todo: fix

//    public Pose2d calculate(Pose2d currentPose) {
//        double xPower = xController.calculate(currentPose.getX());
//        double yPower = yController.calculate(currentPose.getY());
//        double headingPower = getRotate(currentPose.getHeading());
//        return new Pose2d(xPower, yPower);
//    }

    public void setTargetPose(Vector2d targetPosition) {
        xController.setSetPoint(targetPosition.getX());
        yController.setSetPoint(targetPosition.getY());
    }
    public void setTargetPose(Pose2d targetPose) {
        xController.setSetPoint(targetPose.getX());
        yController.setSetPoint(targetPose.getY());
        headingController.setSetPoint(targetPose.getHeading());
    }

    public void update() {
    }

    public void updatePID(){
        xController.setPID(kPX, kIX, kDX);
        yController.setPID(kPY, kIY, kDY);
        headingController.setPID(kPHeading, kIHeading, kDHeading);
    }
    public void setTargetHeading(double targetHeading) {
        headingController.setSetPoint(targetHeading);
    }

    public void init() {
    }

    @Override
    public void telemetry(Telemetry telemetry) {
    }

    public double getRotate(double currHeading){
        if (currHeading - headingController.getSetPoint() < -Math.PI) currHeading += 2*Math.PI;
        else if(currHeading - headingController.getSetPoint() > Math.PI) currHeading -= 2* Math.PI;
        return Range.clip(headingController.calculate(currHeading), -1,1);
    }
}
