package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.intake.arm.IntakeArm;

@Config
@TeleOp(name = "arm rotation test", group = "test")
public class ArmRotateTest extends GreenLinearOpMode {

    IntakeArm arm;
    double power;
    public void runOpMode() throws InterruptedException {

        arm = new IntakeArm(hardwareMap);

        while(opModeInInit()) {
            arm.init();
        }
        waitForStart();

        while(opModeIsActive()) {
            if (gamepad2.a){
               arm.armRotate.setPower(.2);
            } else if (gamepad2.b){
                arm.intake();
           }
//            else {
//                arm.rest();
//            }
            arm.update();
            arm.telemetry(telemetry);
        }

    }

}
