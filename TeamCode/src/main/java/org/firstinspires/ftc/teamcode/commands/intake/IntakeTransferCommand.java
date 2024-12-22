package org.firstinspires.ftc.teamcode.commands.intake;


import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmAssistCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmTransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmVerticalCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.IntakeWristTransferCommand;

/*
- Intake arm vertical
- Intake arm to assist
- Intake wrist to transfer
- Intake arm to transfer
 */
public class IntakeTransferCommand extends SequentialCommandGroup{
    public IntakeTransferCommand() {
        super(
                new SequentialCommandGroup(
                        new IntakeArmVerticalCommand(),
                        new WaitCommand(100),
                        new IntakeArmAssistCommand(),
                        new IntakeWristTransferCommand(),
                        new IntakeArmTransferCommand()
                )
        );
    }
}
