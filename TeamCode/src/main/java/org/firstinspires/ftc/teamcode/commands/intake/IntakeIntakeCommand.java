package org.firstinspires.ftc.teamcode.commands.intake;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmParallelCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmVerticalCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.IntakeWristIntakeCommand;
/*
- Intake wrist adjusted to intake pos
- Intake arm first to vertical -> parallel
 */
public class IntakeIntakeCommand extends SequentialCommandGroup {
    public IntakeIntakeCommand() {
        super(
                new SequentialCommandGroup(
                        new IntakeWristIntakeCommand(),
                        new IntakeArmVerticalCommand(),
                        new WaitCommand(100),
                        new IntakeArmParallelCommand()
                )
        );
    }
}
