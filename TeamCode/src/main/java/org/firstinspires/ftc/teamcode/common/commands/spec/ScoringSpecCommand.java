package org.firstinspires.ftc.teamcode.common.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.VertSlidesLowSpecCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.VertSlidesStartCommand;

/*
- lifts vertical slides to low specimen rung
- opens outtake claw
- lowers vertical slides fully
 */
public class ScoringSpecCommand extends SequentialCommandGroup {
    public ScoringSpecCommand(){
        super (
                new SequentialCommandGroup(
                        new VertSlidesLowSpecCommand(),
                        new WaitCommand(300),
                        new OuttakeClawOpenCommand(),
                        new WaitCommand(600),
                        new VertSlidesStartCommand()
                )
        );
    }
}
