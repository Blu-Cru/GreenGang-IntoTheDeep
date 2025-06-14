package org.firstinspires.ftc.teamcode.greengang.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
@TeleOp(name = "arm test", group = "test")
public class ArmTest extends GreenLinearOpMode {
    @Override
    public void initialize() {
        addClawArm();

    }

    @Override
    public void periodic(){
        if(stickyG1.a){
            new ClawArmInitCommand().schedule();
        }
        else if(stickyG1.b){
            new ClawArmBucketCommand().schedule();
        }
        else if(stickyG1.x){
            new ClawArmInSpecCommand().schedule();
        }
        else if(stickyG1.y) {
            new ClawArmOutSpecCommand().schedule();
        }


    }
}
