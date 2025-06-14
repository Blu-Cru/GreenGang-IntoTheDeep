package org.firstinspires.ftc.teamcode.greengang.common.commands.turn;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class TurnCommand extends InstantCommand {
    public TurnCommand(){
        super(
                () -> Robot.getInstance().drivetrain.driveToHeading(0,0,Math.PI/2)
        );
    }
}
