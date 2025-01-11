package org.firstinspires.ftc.teamcode.roadrunner.drive.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.drive.DrivePID;

@Config
@TeleOp(name = "heading PID Tuner", group = "test")
public class HeadingPIDTuner extends GreenLinearOpMode {
    public static double target;
    public DrivePID pid;
    double x,y, rotate;
    @Override
    public void initialize() {
        enableFTCDashboard();
        addDrivetrain();
    }

    @Override
    public void periodic() {
        pid.updatePID();

        if(!(gamepad1.right_trigger > 0.2)) {
            drivetrain.stop();
        } else {
            x = gamepad1.left_stick_x;
            y = gamepad1.left_stick_y;
            rotate = gamepad1.right_stick_x;
            drivetrain.fieldCentricDrive(x,y,rotate);
//
//            if(gamepad1.a) {
//                pid.pidTo(radToTick(target));
//            }
        }
    }

    public double radToTick(double target){
        return target * 3895.9/(Math.PI*2);
    }

    @Override
    public void telemetry() {
//        telemetry.addData("Target Inches", targetInches);
//        telemetry.addData("Arm current pos", arm.getArmRotatePosition());
    }
}
