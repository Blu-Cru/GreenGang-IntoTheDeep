package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesLowSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

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
                        new VertSlidesStartCommand()
//                        new ConditionalCommand(
//                                new VertSlidesStartCommand(),
//                                new WaitCommand(0),
//                                () -> !Robot.getInstance().distanceSensor.isFull()
//                        )
                )
        );
    }
}
