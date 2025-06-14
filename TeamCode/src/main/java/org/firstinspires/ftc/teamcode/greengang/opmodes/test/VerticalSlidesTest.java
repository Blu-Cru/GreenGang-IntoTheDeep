package org.firstinspires.ftc.teamcode.greengang.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;

@TeleOp(name = "vs test", group = "test")
public class VerticalSlidesTest extends GreenLinearOpMode {

    @Override
    public void initialize() {
        addVertSlides();
    }

    @Override
    public void periodic() {
        double vert;
        vert = -gamepad2.left_stick_y;

        if(Math.abs(vert) > 0.1) {
            vs.manual(.1);
        } else {
            vs.manual(vert);
        }
        telemetry.update();
    }
}
