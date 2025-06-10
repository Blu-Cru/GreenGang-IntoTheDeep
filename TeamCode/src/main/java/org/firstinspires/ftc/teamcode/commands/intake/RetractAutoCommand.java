package org.firstinspires.ftc.teamcode.commands.intake;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.horizSlides.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesStartCommand;

/*
- Claw wrist to transfer pos
- Vert slides lowered
- Opens claw
- Claw arm to transfer pos
 */
public class RetractAutoCommand extends SequentialCommandGroup {
    public RetractAutoCommand() {
        super(
                new SequentialCommandGroup(
                        new SlidesLiftSlightlyCommand(),
                        new WristParallelCommand(),
                        new HorizontalSlidesRetractCommand(),
                        new WaitCommand(400),
                        new VertSlidesStartCommand()
                )
        );
    }
}
