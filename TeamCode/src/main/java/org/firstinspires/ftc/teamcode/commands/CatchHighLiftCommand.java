package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

public class CatchHighLiftCommand extends SequentialCommandGroup {
    public CatchHighLiftCommand(){
        super (
               new SequentialCommandGroup(
                       new OuttakeClawCloseCommand(),
                       new VertSlidesHighLiftCommand()
               )
        );
    }
}
