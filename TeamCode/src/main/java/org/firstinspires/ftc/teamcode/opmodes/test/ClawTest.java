package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;

@TeleOp(name = "claw test",group = "TeleOp")
public class ClawTest extends GreenLinearOpMode {

    Transfer claw;

    //@Override
    public void runOpMode() throws InterruptedException {
        addTransfer();

        claw.init();

        while(opModeInInit()) {
            telemetry.update();
        }

        waitForStart();

        while(opModeIsActive()) {

            if (gamepad1.left_trigger >.2){
                claw.clawSetPower(1); //TODO: Test on FTC Dash to get correct num
            }
            else {
                claw.clawSetPower(0); //TODO: Test on FTC Dash to get correct num
            }

            telemetry.update();
        }

    }
}

