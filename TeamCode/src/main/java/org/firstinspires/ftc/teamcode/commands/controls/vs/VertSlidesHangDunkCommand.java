package org.firstinspires.ftc.teamcode.commands.controls.vs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class VertSlidesHangDunkCommand extends InstantCommand {
    public VertSlidesHangDunkCommand(){
        super(
                () -> Robot.getInstance().vs.hangDunk()
        );
        addRequirements((Subsystem) Robot.getInstance().vs);
    }
}
