package org.firstinspires.ftc.teamcode.common.commands.controls.intake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

public class IntakeTransferCommand extends InstantCommand{
    public IntakeTransferCommand(){
        super(
                () -> {
                    if(Robot.getInstance().color.isFull()){
                        Robot.getInstance().intake.in();
                        new WaitCommand(300).schedule();
                        Robot.getInstance().intake.stop();
                    }
                }
        );

        addRequirements((Subsystem) Robot.getInstance().intake);
    }
}
