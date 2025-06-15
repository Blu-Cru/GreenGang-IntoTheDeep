package org.firstinspires.ftc.teamcode.greengang.common.commands.spec;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInspecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawLooseCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristInSpecTransferCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class SpecIntakeCommand extends SequentialCommandGroup {
    public SpecIntakeCommand(){
        super(
                new ClawWristInSpecTransferCommand(),
                new WaitCommand(200),
                new ClawArmInspecTransferCommand(),
                new WaitCommand(200),
                new ClawWristInSpecCommand(),
                new ClawArmInSpecCommand(),
                new TurretInitCommand(),
                new VertSlidesStartCommand(),
                new OuttakeClawOpenCommand()
        );
    }
}
