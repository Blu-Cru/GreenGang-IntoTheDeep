package org.firstinspires.ftc.teamcode.commands.turnCommand;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class TurnCommand extends InstantCommand {
    public TurnCommand(){
        super(
                () -> Robot.getInstance().drivetrain.driveToHeading(0,0,Math.PI/2)
        );
    }
}
