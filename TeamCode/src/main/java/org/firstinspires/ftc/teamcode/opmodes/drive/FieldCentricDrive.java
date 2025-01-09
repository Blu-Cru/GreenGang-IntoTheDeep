package org.firstinspires.ftc.teamcode.opmodes.drive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;

@TeleOp(name = "Field centric test",group = "TeleOp")
public class FieldCentricDrive extends GreenLinearOpMode {
    Drivetrain drivetrain;
    double y, x, rx;

    @Override
    public void initialize() {
        addDrivetrain();
    }

    @Override
    public void periodic()  {

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
           gamepad1.rumble(150);
       }

       drivetrain.fieldCentricDrive(x, y, rx);
       drivetrain.telemetry(telemetry);
    }

}
