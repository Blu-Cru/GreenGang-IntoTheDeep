package org.firstinspires.ftc.teamcode.commands;


import com.arcrobotics.ftclib.command.SequentialCommandGroup;

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
