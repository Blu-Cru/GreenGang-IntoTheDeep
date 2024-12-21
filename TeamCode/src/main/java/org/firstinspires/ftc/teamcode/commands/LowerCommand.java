package org.firstinspires.ftc.teamcode.commands;


import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowerCommand;

public class LowerCommand extends SequentialCommandGroup{
    public LowerCommand() {
        super(
                new SequentialCommandGroup(
                        new OuttakeClawCloseCommand(),
                        new VertSlidesLowerCommand()
                )
        );
    }
}
