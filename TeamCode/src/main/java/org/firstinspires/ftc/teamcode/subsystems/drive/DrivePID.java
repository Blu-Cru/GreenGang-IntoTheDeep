package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.GreenSubsystem;

public class DrivePID implements GreenSubsystem {
    public double
            kPX = 8, kIX = 0, kDX = 0,
            kPY = 8, kIY = 0, kDY = 0,
            kPHeading = 0, kIHeading = 0, kDHeading = 0;

    public PIDController headingController;
    public double currHeading;
    SampleMecanumDrive drive;

    public DrivePID(HardwareMap hardwareMap) {
        headingController = new PIDController(kPHeading, kIHeading, kDHeading);
        drive = new SampleMecanumDrive(hardwareMap);
    }

    public void update(){
    }
    public void updatePID() {
        headingController.setPID(kPHeading, kIHeading, kDHeading);
    }
    public void setTargetHeading(double targetHeading) {
        headingController.setSetPoint(targetHeading);
    }

    public void stopPid(){
        headingController.setSetPoint(drive.getExternalHeading());
    }

    public void init() {
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("heading ", currHeading);
        telemetry.update();
    }

    public double getRotate(){
        currHeading = drive.getExternalHeading();
        if (currHeading - headingController.getSetPoint() < -Math.PI) currHeading += 2*Math.PI;
        else if(currHeading - headingController.getSetPoint() > Math.PI) currHeading -= 2* Math.PI;
        return Range.clip(headingController.calculate(currHeading), -1,1);
    }
}
