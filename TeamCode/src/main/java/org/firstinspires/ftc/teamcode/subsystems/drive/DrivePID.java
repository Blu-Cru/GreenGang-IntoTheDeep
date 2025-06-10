package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.util.Angle;
import com.arcrobotics.ftclib.controller.PDController;
import com.arcrobotics.ftclib.controller.PIDController;
//import com.arcrobotics.ftclib.geometry.Pose2d;

import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;

@Config
public class DrivePID {
    public static double
            kPX = 8, kIX = 0, kDX = 0, // todo: implement this & kY
            kPY = 8, kIY = 0, kDY = 0,
            kPHeading = 1, kIHeading = 0.09, kDHeading = 0.16; // tuned

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

    public Pose2d calculate(Drivetrain dt) {
        double xPower = xController.calculate(dt.xState.getX());
        double yPower = yController.calculate(dt.yState.getY());
        double headingPower = getRotate(dt.heading);
        return new Pose2d(xPower, yPower, headingPower);
    }

    public double calcX(Vector2d xState) {
        return xController.calculate(xState.getX());
    }

    public double calcY(Vector2d yState) {
        return yController.calculate(yState.getY());
    }

//    public void setTargetPose(Vector2d targetPosition) {
//        xController.setSetPoint(targetPosition.getX());
//        yController.setSetPoint(targetPosition.getY());
//    }
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

    public double getRotate(Drivetrain dt) {
        return getRotate(dt.headingState);
    }
    public double getRotate(Vector2d pv) {
        return getRotate(pv, new Vector2d(headingController.getSetPoint(), 0));
    }
    public double getRotate(Vector2d pv, Vector2d sp) {
        pv = new Vector2d(Angle.normDelta(pv.getX() - sp.getX()) + sp.getX(), pv.getY());
        return headingController.calculate(pv.getX(), sp.getX());
    }


    public double getRotate(double heading) {
        if (heading - headingController.getSetPoint() < -Math.PI) heading += 2 * Math.PI;
        else if (heading - headingController.getSetPoint() > Math.PI) heading -= 2 * Math.PI;

        return Range.clip(headingController.calculate(heading), -1, 1);
    }

}
