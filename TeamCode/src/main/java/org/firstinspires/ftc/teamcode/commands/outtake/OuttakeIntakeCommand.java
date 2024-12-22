package org.firstinspires.ftc.teamcode.commands.outtake;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;

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
                        new VertSlidesStartCommand(),
                        new OuttakeClawOpenCommand(),
                        new ClawArmIntakeCommand()
                )
        );
    }
}
