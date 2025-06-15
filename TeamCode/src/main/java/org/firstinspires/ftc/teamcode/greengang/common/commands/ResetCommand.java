package org.firstinspires.ftc.teamcode.greengang.common.commands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInspecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.greengang.common.subsystems.outtake.arm.ClawArm;
import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

/*
resets all subsystems to how they were in initialization state
 */
public class ResetCommand extends SequentialCommandGroup {
    public ResetCommand(){
        super (

                new ConditionalCommand(

                        new SequentialCommandGroup(
                                new ClawArmInspecTransferCommand(),
                                new ClawWristInSpecTransferCommand(),
                                new WaitCommand(300),
                                new ClawArmInitCommand(),
                                new WaitCommand(300),
                                new ClawWristTransferCommand()
                        ),

                        new SequentialCommandGroup(
                                new ClawArmInitCommand(),
                                new ClawWristTransferCommand()
                        ),

                        () -> Robot.getInstance().clawArm.state== ClawArm.STATE.INSPEC
                ),


                new HorizontalSlidesRetractCommand(),
                new TurretInitCommand(),
                new WristParallelCommand(),
                new OuttakeClawOpenCommand(),

                new ConditionalCommand(
                        new WaitCommand(1000),//true
                        new WaitCommand(50),//false
                        () -> Robot.getInstance().vs.getVScurrRightPos() < 500
                ),

                new VertSlidesStartCommand() //may need to swap
        );
    }

}
