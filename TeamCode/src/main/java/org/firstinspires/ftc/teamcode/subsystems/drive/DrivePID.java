package org.firstinspires.ftc.teamcode.subsystems.drive;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.util.Angle;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.util.Range;

public class DrivePID {
    public static double
            kPX = 0, kIX = 0, kDX = 0,
            kPY = 0, kIY = 0, kDY = 0,
            kPHeading = 0, kIHeading = 0, kDHeading = 0;

    public PIDController headingController;

    public DrivePID() {
        headingController = new PIDController(kPHeading, kIHeading, kDHeading);
    }

    public Pose2d calculate(Pose2d currentPose) {
        double headingPower = getRotate(currentPose.getHeading());
        return new Pose2d(headingPower);
    }
    public void updatePID() {
        headingController.setPID(kPHeading, kIHeading, kDHeading);
    }
    public void setTargetHeading(double targetHeading) {
        headingController.setSetPoint(targetHeading);
    }
    public double getRotate(double heading) {
        if(heading - headingController.getSetPoint() < -Math.PI) heading += 2*Math.PI;
        else if(heading - headingController.getSetPoint() > Math.PI) heading -= 2 * Math.PI;

        return Range.clip(headingController.calculate(heading), -1, 1);
    }


}
