package org.firstinspires.ftc.teamcode.opmodes.trajectories;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.transfer;

@TeleOp(name = "claw test",group = "TeleOp")
public class clawTest extends LinearOpMode {

    transfer claw;

    @Override
    public void runOpMode() throws InterruptedException {
        claw = new transfer(hardwareMap);

        claw.init();

        while(opModeInInit()) {
            telemetry.update();
        }

        waitForStart();

        while(opModeIsActive()) {

            if (gamepad1.left_trigger >.2){
                claw.clawSetPower(1);
            }
            else if{
                claw.clawSetPower(0);
            }

            telemetry.update();
        }

    }
}

