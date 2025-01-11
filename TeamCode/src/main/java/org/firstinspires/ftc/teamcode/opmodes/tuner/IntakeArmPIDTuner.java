package org.firstinspires.ftc.teamcode.opmodes.tuner;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;

@Config
@TeleOp(name = "intakeArm PID Tuner", group = "test")
public class IntakeArmPIDTuner extends GreenLinearOpMode {
    public static double targetInches;

    @Override
    public void initialize() {
        enableFTCDashboard();
    }

    @Override
    public void periodic() {
//        arm.updatePID();
//
//        if(!(gamepad1.right_trigger > 0.2)) {
//            arm.stopArmRotate();
//        } else {
//            arm.update();
//
//            if(gamepad1.a) {
//                arm.pidTo(radToTick(targetInches));
//            }
//        }
    }

    public double radToTick(double target){
        return target * 3895.9/(Math.PI*2);
    }

    @Override
    public void telemetry() {
        telemetry.addData("Target Inches", targetInches);
//        telemetry.addData("Arm current pos", arm.getArmRotatePosition());
    }
}
