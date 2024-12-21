package org.firstinspires.ftc.teamcode.opmodes.drive;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.controller.PIDController;

public class PDController extends PIDController {
    Vector2d k;
    public PDController(double kP, double kI, double kD) {
        super(kP, kI, kD);
        k = new Vector2d(kP, kD);
    }


    public double calculate(Vector2d pv, Vector2d sp) {
        Vector2d error = sp.minus(pv);
        return error.dot(k);
    }

    public double calculate(Vector2d pv, MotionProfile profile) {
        Vector2d sp = profile.getInstantState();
        return calculate(pv, sp);
    }

    @Override
    public void setPID(double kp, double ki, double kd) {
        super.setPID(kp, ki, kd);
        k = new Vector2d(kp, kd);
    }
}