package org.firstinspires.ftc.teamcode.greengang.common.commands.bucket;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.greengang.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

/*
- opens claw (deposits sample)
- returns robot to init state
 */
public class DropDepositCommand extends SequentialCommandGroup {
    public DropDepositCommand(){
        super(
                new SequentialCommandGroup(
                        new OuttakeClawOpenCommand(),
                        new ResetCommand(),
                        new ConditionalCommand(
                                new ResetCommand(),
                                new WaitCommand(0),
                                () -> !Robot.getInstance().distanceSensor.isFull()
                        )
                )
        );
    }
}
