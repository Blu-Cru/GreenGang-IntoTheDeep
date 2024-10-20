package org.firstinspires.ftc.teamcode.testing;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Intake;

@TeleOp(name = "claw test",group = "TeleOp")
public class IntakeTest extends LinearOpMode {

    Intake intake;

    @Override
    public void runOpMode() throws InterruptedException {
        intake = new Intake(hardwareMap);

        intake.init();

        while(opModeInInit()) {
            telemetry.update();
        }

        waitForStart();

        while(opModeIsActive()) {

            if(gamepad1.left_bumper) {
                intake.intakeSetPower(1);
            }
            else if(gamepad1.right_bumper) {
                intake.intakeSetPower(-1);
            }
            else{
                intake.intakeSetPower(0);
            }

            telemetry.update();
        }

    }
}
