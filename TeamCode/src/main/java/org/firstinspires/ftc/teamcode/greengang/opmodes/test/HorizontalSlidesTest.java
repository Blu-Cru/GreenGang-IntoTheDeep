package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;

@TeleOp(name = "hs test", group = "test")
public class HorizontalSlidesTest extends GreenLinearOpMode {

    @Override
    public void initialize() {
        addHorizontalSlides();
    }

    @Override
    public void periodic() {
        double hsPow;
        hsPow = -gamepad2.left_stick_y;
        if (Math.abs(hsPow) > .1) {
            horizontalSlides.manualSlide(hsPow);
        }

    }
}
