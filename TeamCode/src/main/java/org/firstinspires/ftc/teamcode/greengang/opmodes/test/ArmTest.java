package org.firstinspires.ftc.teamcode.greengang.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.greengang.opmodes.GreenLinearOpMode;

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
            clawArm.transfer();
        }
        else if(stickyG1.b){
            clawArm.sampleOuttake();
        }
        else if(stickyG1.x){
            clawArm.specOuttake();
        }
        else if(stickyG1.y) {
            clawArm.inSpec();
        }


    }
}
