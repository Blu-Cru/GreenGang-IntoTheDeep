package org.firstinspires.ftc.teamcode.commands.bucket;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

/*
- opens claw (deposits sample)
- returns robot to init state
 */
public class DropDepositCommand extends SequentialCommandGroup {
    public DropDepositCommand(){
        super(
                new SequentialCommandGroup(
                        new OuttakeClawOpenCommand(),
                        new ConditionalCommand(
                                new ResetCommand(),
                                new WaitCommand(0),
                                () -> !Robot.getInstance().distanceSensor.isFull()
                        )
                )
        );
    }
}
