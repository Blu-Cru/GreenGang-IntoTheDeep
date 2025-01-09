package org.firstinspires.ftc.teamcode.commands.controls.hs;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class HorizontalSlidesExtendCommand extends InstantCommand {
    public HorizontalSlidesExtendCommand(){
        super(
                () -> {
                    Robot.getInstance().horizontalSlides.autoLower();
                }
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
