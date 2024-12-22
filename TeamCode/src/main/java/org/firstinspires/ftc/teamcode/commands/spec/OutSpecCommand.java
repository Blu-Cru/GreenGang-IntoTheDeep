package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighSpecCommand;

public class OutSpecCommand extends SequentialCommandGroup {
    public OutSpecCommand(){
        super (
                new SequentialCommandGroup( //
                        new OuttakeClawCloseCommand(),
                        new WaitCommand(500),
                        new VertSlidesHighSpecCommand()
                )
        );
    }
}
