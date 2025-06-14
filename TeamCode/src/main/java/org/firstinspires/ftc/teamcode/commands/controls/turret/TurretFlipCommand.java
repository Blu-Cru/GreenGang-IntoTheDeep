package org.firstinspires.ftc.teamcode.commands.controls.turret;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class TurretFlipCommand extends InstantCommand {
    public TurretFlipCommand(){
        super(
                () -> Robot.getInstance().turret.flip()
        );



        addRequirements((Subsystem) Robot.getInstance().turret);
    }
}
