package org.firstinspires.ftc.teamcode.opmodes.trajectories;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Intake;

@TeleOp(name = "claw test",group = "TeleOp")
public class clawTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain(hardwareMap);
        claw = new transfer(hardwareMap);

        claw.init();

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


            if(gamepad1.left_bumper) {
                intake.IntakeSetPower(1);
            }
            else if(gamepad1.right_bumper) {
                intake.IntakeSetPower(-1);
            }
            else{
                intake.IntakeSetPower(0);
            }

            drivetrain.fieldCentricDrive(x, y, rx);
            drivetrain.telemetry(telemetry);
            telemetry.update();
        }

    }
}
}