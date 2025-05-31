package org.firstinspires.ftc.teamcode.commands.controls.colorSensor;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class ColorSensorCommand extends InstantCommand {

    public ColorSensorCommand(){
        super(
                () -> Robot.getInstance().color.init()
        );

        addRequirements((Subsystem) Robot.getInstance().color);

    }
}
