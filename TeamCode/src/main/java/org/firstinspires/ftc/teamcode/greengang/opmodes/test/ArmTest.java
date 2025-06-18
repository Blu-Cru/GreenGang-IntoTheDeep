package org.firstinspires.ftc.teamcode.greengang.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInspecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristHighOutSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretFlipCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretTurn90Command;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.greengang.common.subsystems.outtake.arm.ClawArm;
import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;
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
        addDrivetrain();
        addIntake();
        addStickyG1();
        addClawArm();
        addOuttakeClaw();
        addHorizontalSlides();
        addIntakeWrist();
        addClawWrist();
        addVertSlides();
        addHang();
        addIntakeColorSensor();
        addTurret();
        addClawDistanceSensor();

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

        if(stickyG2.a){
            new SequentialCommandGroup(
                    new ConditionalCommand(

                            new SequentialCommandGroup(
                                    new ClawWristInSpecTransferCommand(),
//                                    new WaitCommand(200),
                                    new ClawArmInspecTransferCommand()
//                                    new WaitCommand(200)
                            ),

                            new WaitCommand(0),

                            () -> Robot.getInstance().clawArm.state== ClawArm.STATE.INIT
                    ),

//                    new ClawWristInSpecCommand(),
                    new ClawArmInSpecCommand(),
                    new TurretTurn90Command(),
                    new TurretInitCommand(),
                    new VertSlidesStartCommand(),
                    new OuttakeClawOpenCommand()
            ).schedule();
        } else if (stickyG2.b){
            new SequentialCommandGroup(
                    new ClawArmOutSpecCommand(),
                    new VertSlidesHighSpecCommand(),
//                new WaitCommand(300),
//                    new ClawWristHighOutSpecCommand(),
                    new OuttakeClawCloseCommand(),
                    new TurretFlipCommand()
            ).schedule();
        }


    }
}
