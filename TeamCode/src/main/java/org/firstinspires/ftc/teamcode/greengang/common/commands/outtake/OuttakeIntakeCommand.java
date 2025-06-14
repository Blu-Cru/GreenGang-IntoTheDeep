package org.firstinspires.ftc.teamcode.greengang.common.commands.outtake;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmInitCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristTransferCommand;

/*
- Claw wrist to transfer pos
- Vert slides lowered
- Opens claw
- Claw arm to transfer pos
 */
public class OuttakeIntakeCommand extends SequentialCommandGroup {
    public OuttakeIntakeCommand() {
        super(
                new SequentialCommandGroup(
                        new ClawWristTransferCommand(),
                        new OuttakeClawOpenCommand(),
                        new ClawArmInitCommand()
                )
        );
    }
}
