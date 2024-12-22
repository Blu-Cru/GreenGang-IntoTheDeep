package org.firstinspires.ftc.teamcode.commands;


import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmAssistCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmTransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmVerticalCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.IntakeWristTransferCommand;

public class Intake_TransferringCommand extends SequentialCommandGroup{
    public Intake_TransferringCommand() {
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
