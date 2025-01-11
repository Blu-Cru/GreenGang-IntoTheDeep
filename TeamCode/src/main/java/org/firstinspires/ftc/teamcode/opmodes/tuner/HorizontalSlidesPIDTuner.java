package org.firstinspires.ftc.teamcode.opmodes.tuner;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;

@Config
@TeleOp(name = "hs PID Tuner", group = "test")
public class HorizontalSlidesPIDTuner extends GreenLinearOpMode {
    public static double targetHeight;

    @Override
    public void initialize() {
        enableFTCDashboard();
        addHorizontalSlides();
    }

    @Override
    public void periodic() {
        horizontalSlides.updatePID();
        if(!(gamepad1.right_trigger > 0.2)) {
            horizontalSlides.stop();
        } else {
//            horizontalSlides.update();
            if(gamepad1.a) {
                horizontalSlides.pidTo(targetHeight);
            }
        }
    }

    @Override
    public void telemetry() {
        telemetry.addData("Target TICKS ", targetHeight);
    }
}
