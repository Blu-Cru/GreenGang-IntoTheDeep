package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.outtake.ClawArm;

@TeleOp(name = "claw test",group = "test")
public class TransferTest extends GreenLinearOpMode {
    ClawArm claw;
    public void runOpMode() throws InterruptedException {

        claw = new ClawArm(hardwareMap);

        while(opModeInInit()) {
            claw.init();
        }

        waitForStart();

        while(opModeIsActive()) {

            if (gamepad2.right_trigger >.2){
                //.clawArmSetPos(1); //TODO: Test on FTC Dash to get correct num
            }
            else {
                //claw.clawArmSetPos(0); //TODO: Test on FTC Dash to get correct num
            }

            claw.telemetry(telemetry);
        }

    }
}

