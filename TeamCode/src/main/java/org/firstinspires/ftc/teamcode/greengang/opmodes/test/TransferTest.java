package org.firstinspires.ftc.teamcode.greengang.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;

@Disabled
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

            clawArm.telemetry(telemetry);
    }
}

