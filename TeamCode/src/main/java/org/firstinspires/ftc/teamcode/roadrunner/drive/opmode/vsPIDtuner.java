package org.firstinspires.ftc.teamcode.roadrunner.drive.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.intake.arm.IntakeArm;

@Config
@TeleOp(name = "vs PID Tuner", group = "test")
public class vsPIDtuner extends GreenLinearOpMode {
    public static double targetHeight;

    @Override
    public void initialize() {
        enableFTCDashboard();
        addVertSlides();
    }

    @Override
    public void periodic() {
        vs.updatePID();

        if(!(gamepad1.right_trigger > 0.2)) {
            vs.stopVSrotate();
        } else {
            vs.update();

            if(gamepad1.a) {
                vs.pidTo(targetHeight);
            }
        }
    }

    @Override
    public void telemetry() {
        telemetry.addData("Target Inches", targetHeight);
        telemetry.addData("Arm current pos", vs.motorRight.getCurrentPosition());
    }
}
