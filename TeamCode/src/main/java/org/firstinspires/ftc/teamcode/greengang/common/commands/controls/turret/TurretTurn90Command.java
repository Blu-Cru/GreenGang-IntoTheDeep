package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class TurretTurn90Command extends InstantCommand {
    public TurretTurn90Command(){
        super(
                () -> Robot.getInstance().turret.turn90()
        );



        addRequirements(Robot.getInstance().turret);
    }
}
