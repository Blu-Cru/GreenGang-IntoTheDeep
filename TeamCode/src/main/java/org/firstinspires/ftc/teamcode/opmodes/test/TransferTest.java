package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.outtake.arm.ClawArm;

@TeleOp(name = "claw test",group = "test")
public class TransferTest extends GreenLinearOpMode {

    @Override
    public void initialize() {
        addClawArm();
    }

    @Override
    public void periodic()  {
            if (gamepad2.right_trigger >.2){
                //.clawArmSetPos(1); //TODO: Test on FTC Dash to get correct num
            }
            else {
                //claw.clawArmSetPos(0); //TODO: Test on FTC Dash to get correct num
            }

            transfer.telemetry(telemetry);
    }
}

