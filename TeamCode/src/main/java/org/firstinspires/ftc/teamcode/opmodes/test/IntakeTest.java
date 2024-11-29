package org.firstinspires.ftc.teamcode.opmodes.test;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.wrist.IntakeWrist;

@TeleOp(name = "intake test",group = "TeleOp")
public class IntakeTest extends GreenLinearOpMode {
    Intake intake;
    IntakeWrist wrist;

    @Override
    public void runOpMode() throws InterruptedException {

        //addIntake();
        intake = new Intake(hardwareMap);
        wrist = new IntakeWrist(hardwareMap);

        intake.init();
        wrist.init();

        while(opModeInInit()) {
            telemetry.update();
        }

        waitForStart();

        while(opModeIsActive()) {

            if(gamepad1.left_bumper) {
                intake.in();
            }
            else if(gamepad1.right_bumper) {
                intake.spit();
            }
            else{
                intake.stop();
            }

            if (gamepad2.left_bumper){
                wrist.intake();
            } else if (gamepad2.right_bumper){
                wrist.transfer();
            }

            telemetry.update();
        }

    }
}
