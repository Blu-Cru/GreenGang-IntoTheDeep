package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighLiftCommand;

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
