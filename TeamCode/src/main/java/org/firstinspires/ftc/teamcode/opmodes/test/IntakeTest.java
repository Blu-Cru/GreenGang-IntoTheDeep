package org.firstinspires.ftc.teamcode.opmodes.test;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.wrist.IntakeWrist;

@TeleOp(name = "intake test",group = "TeleOp")
public class IntakeTest extends GreenLinearOpMode {

    public void initialize(){
        addIntake();
        addIntakeWrist();
    }
    @Override
    public void periodic() {

        if(gamepad1.left_bumper) {
            intake.in();
        }
        else if(gamepad1.right_bumper) {
            intake.spit();
        }
        else{
            intake.stop();
        }

        if (gamepad2.left_bumper){
            intakeWrist.intake();
        } else if (gamepad2.right_bumper){
            intakeWrist.transfer();
        }


    }
}
