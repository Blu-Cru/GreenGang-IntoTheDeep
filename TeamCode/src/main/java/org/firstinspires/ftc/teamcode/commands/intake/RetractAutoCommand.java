package org.firstinspires.ftc.teamcode.commands.intake;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;

/*
- Claw wrist to transfer pos
- Vert slides lowered
- Opens claw
- Claw arm to transfer pos
 */
public class RetractAutoCommand extends SequentialCommandGroup {
    public RetractAutoCommand() {
        super(
                new SequentialCommandGroup(
                        new SlidesLiftSlightlyCommand(),
                        new HorizontalSlidesRetractCommand(),
                        new WaitCommand(800),
                        new VertSlidesStartCommand()
                )
        );
    }
}
