package org.firstinspires.ftc.teamcode.commands.bucket.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.intake.IntakeIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;
/*
- Intakes piece (intake spins for 3 seconds)
- Transfers piece into claw
 */

public class AutoSamplePart1 extends SequentialCommandGroup {
    public AutoSamplePart1(){
        super(
                new SequentialCommandGroup(
                        new IntakeIntakeCommand(),
                        new TransferCommand()
                )
        );
    }
}
