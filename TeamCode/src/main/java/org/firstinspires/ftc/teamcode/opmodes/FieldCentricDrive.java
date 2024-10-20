package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

//@Config
@TeleOp(name = "Field centric test",group = "TeleOp")
public class FieldCentricDrive extends LinearOpMode {
    Drivetrain drivetrain;
    double y;
    double x;
    double rx;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(hardwareMap);

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

           if (gamepad1.options) {
               drivetrain.setExternalHeading(Math.toRadians(90));
           }

           drivetrain.fieldCentricDrive(x, y, rx);
           drivetrain.telemetry(telemetry);
           telemetry.update();
        }
    }
}
