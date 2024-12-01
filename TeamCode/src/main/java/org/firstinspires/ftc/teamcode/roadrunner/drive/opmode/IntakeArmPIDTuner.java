package org.firstinspires.ftc.teamcode.roadrunner.drive.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.intake.arm.IntakeArm;

@Config
@TeleOp(name = "intakeArm PID Tuner", group = "test")
public class IntakeArmPIDTuner extends GreenLinearOpMode {
    public static double targetInches;

    @Override
    public void initialize() {
        enableFTCDashboard();
        addIntakeArm();
    }

    @Override
    public void periodic() {
        arm.updatePID();

        if(!(gamepad1.right_trigger > 0.2)) {
            arm.stopArmRotate();
        } else {
            arm.update();

            if(gamepad1.a) {
                arm.pidTo(targetInches);
            }
        }
    }

    @Override
    public void telemetry() {
        telemetry.addData("Target Inches", targetInches);
    }
}
