package org.firstinspires.ftc.teamcode.opmodes.drive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;

@TeleOp(name = "Robot centric drive",group = "TeleOp")
public class RobotCentricDrive extends GreenLinearOpMode {
    Drivetrain drivetrain;
    double y, x, rx, power;

    @Override
    public void runOpMode() throws InterruptedException {

        addDrivetrain();

        while(opModeInInit()) {
            telemetry.update();
        }

        waitForStart();

        while(opModeIsActive()) {

            y = -gamepad1.left_stick_y;
            x = gamepad1.left_stick_x;
            rx = -gamepad1.right_stick_x;

            //Robot moves slower
            if(gamepad1.right_trigger > 0.4) {
                Drivetrain.drivePower = 0.3;
            }
            else {
                Drivetrain.drivePower = 0.6;
            }

            drivetrain.drive(x, y, rx);

        }
    }
}

