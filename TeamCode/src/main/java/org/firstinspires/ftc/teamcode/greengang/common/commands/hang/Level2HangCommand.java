package org.firstinspires.ftc.teamcode.greengang.common.commands.hang;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class Level2HangCommand extends InstantCommand {
    public Level2HangCommand(){
        super(
                () -> Robot.getInstance().vs.ascent2()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
