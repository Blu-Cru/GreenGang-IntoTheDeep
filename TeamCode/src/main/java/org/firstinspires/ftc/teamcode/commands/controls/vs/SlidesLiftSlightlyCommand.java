package org.firstinspires.ftc.teamcode.commands.controls.vs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

/*
- lifts vertical slides to low specimen rung
- opens outtake claw
- lowers vertical slides fully
 */
public class SlidesLiftSlightlyCommand extends InstantCommand {
    public SlidesLiftSlightlyCommand(){
        super (
                () -> Robot.getInstance().vs.slightLift()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
