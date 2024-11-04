package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.slides.VertSlides;

@Config
@TeleOp(name = "vert slides test",group = "TeleOp")
public class VertSlidesTest extends LinearOpMode {
    VertSlides vs;
    public static double targetPos = 0.0;
    @Override
    public void runOpMode() throws InterruptedException {

        vs = new VertSlides(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        while(opModeInInit()) {
            vs.init();
        }

        waitForStart();

        while(opModeIsActive()) {
            vs.setTargetPos(targetPos);
            vs.update();
            vs.telemetry(telemetry);
            telemetry.update();
        }

    }
}
