package org.firstinspires.ftc.teamcode.commands.outtake;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.wrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.claw.OuttakeClawOpenCommand;

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
                        new ClawWristIntakeCommand(),
                        new OuttakeClawOpenCommand(),
                        new ClawArmIntakeCommand()
                )
        );
    }
}
