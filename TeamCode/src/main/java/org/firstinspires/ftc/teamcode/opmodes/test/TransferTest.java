package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.transfer.Transfer;

@TeleOp(name = "claw test",group = "test")
public class TransferTest extends GreenLinearOpMode {
    Transfer claw;
    public void runOpMode() throws InterruptedException {

        claw = new Transfer(hardwareMap);

        while(opModeInInit()) {
            claw.init();
        }

        waitForStart();

        while(opModeIsActive()) {

            if (gamepad2.right_trigger >.2){
                claw.clawSetPower(1); //TODO: Test on FTC Dash to get correct num
            }
            else {
                claw.clawSetPower(0); //TODO: Test on FTC Dash to get correct num
            }

            claw.telemetry(telemetry);
        }

    }
}

