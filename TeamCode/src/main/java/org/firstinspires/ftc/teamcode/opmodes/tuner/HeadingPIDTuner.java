package org.firstinspires.ftc.teamcode.opmodes.tuner;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;

@Config
@TeleOp(name = "vs PID Tuner", group = "test")
public class HeadingPIDTuner extends GreenLinearOpMode {
    public static double targetHeading;

    @Override
    public void initialize() {
        enableFTCDashboard();
        addDrivePID();
        addDrivetrain();
    }

    @Override
    public void periodic() {
        dtPid.updatePID();
        double x = -gamepad1.left_stick_x;
        double y= -gamepad1.left_stick_y;

        if((gamepad1.right_trigger > 0.2)) {
            dtPid.getRotate();
            if(gamepad1.a) {
                drivetrain.driveToHeading(x,y,targetHeading);
            }
        }
    }

    @Override
    public void telemetry() {
        telemetry.addData("target heading", targetHeading);
        telemetry.addData("heading current pos", dtPid.currHeading);
    }
}
