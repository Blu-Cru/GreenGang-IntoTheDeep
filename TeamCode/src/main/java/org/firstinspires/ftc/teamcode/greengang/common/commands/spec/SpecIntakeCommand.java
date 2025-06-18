package org.firstinspires.ftc.teamcode.greengang.common.commands.spec;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInspecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawLooseCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretTurn90Command;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.greengang.common.subsystems.outtake.arm.ClawArm;
import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class SpecIntakeCommand extends SequentialCommandGroup {
    public SpecIntakeCommand(){
        super(
                new ConditionalCommand(

                        new SequentialCommandGroup(
//                                new ClawWristInSpecTransferCommand(),
                                new WaitCommand(200),
                                new ClawArmInspecTransferCommand(),
                                new WaitCommand(200)
                        ),

                        new SequentialCommandGroup(
                                new WaitCommand(0)
                        ),

                        () -> Robot.getInstance().clawArm.state== ClawArm.STATE.INIT
                ),

//                new ClawWristInSpecCommand(),
                new ClawArmInSpecCommand(),
                new TurretTurn90Command(),
                new TurretInitCommand(),
                new VertSlidesStartCommand(),
                new OuttakeClawOpenCommand()
        );
    }
}
