package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class TurretInitCommand extends InstantCommand {
    public TurretInitCommand(){
        super(
                () -> Robot.getInstance().turret.init()
        );



        addRequirements((Subsystem) Robot.getInstance().turret);
    }
}
