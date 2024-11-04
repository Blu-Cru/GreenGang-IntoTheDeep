package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.arm.Arm;

@Config
@TeleOp(name = "arm rotation test", group = "test")
public class ArmRotateTest extends GreenLinearOpMode {

    Arm arm;

    public static int position = 40;
    double power;
    public void runOpMode() throws InterruptedException {

        arm = new Arm(hardwareMap);

        while(opModeInInit()) {
            arm.init();
        }
        waitForStart();

        while(opModeIsActive()) {

            power = -gamepad2.left_trigger;
            if (power > .75) { power = .75; }
            arm.setArmRotatePower(power);
            arm.telemetry(telemetry);

        }

    }

}
