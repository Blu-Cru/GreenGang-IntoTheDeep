package org.firstinspires.ftc.teamcode.common.commands.outtake;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.common.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.clawWrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawOpenCommand;

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
