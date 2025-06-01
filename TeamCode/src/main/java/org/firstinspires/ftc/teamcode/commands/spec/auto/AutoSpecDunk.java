package org.firstinspires.ftc.teamcode.commands.spec.auto;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowSpecCommand;
public class AutoSpecDunk extends SequentialCommandGroup{
    //second part of auto speicmen outtake
    public AutoSpecDunk(){
        super(
                new SequentialCommandGroup(
                        new VertSlidesLowSpecCommand(),
                        new WaitCommand(500),//previous 2000
                        new OuttakeClawOpenCommand()
                )
        );

    }


}
