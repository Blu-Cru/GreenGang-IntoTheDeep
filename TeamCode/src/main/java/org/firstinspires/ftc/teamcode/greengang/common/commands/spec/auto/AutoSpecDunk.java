package org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesLowSpecCommand;

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
