package org.firstinspires.ftc.teamcode.common.commands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.common.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.clawWrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.hs.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intake.IntakeTransferCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.common.subsystems.Robot;
import org.firstinspires.ftc.teamcode.common.subsystems.outtake.arm.ClawArm;
import org.firstinspires.ftc.teamcode.common.subsystems.slides.HorizontalSlides;

/*
resets all subsystems to how they were in initialization state
 */
public class ResetCommand extends SequentialCommandGroup {
    public ResetCommand(){
        super (

                new ParallelCommandGroup(
                    new OuttakeClawOpenCommand(),
                    new ClawWristIntakeCommand(),
                    new ClawArmIntakeCommand(),
                        new SlidesLiftSlightlyCommand()
                ),
                new ParallelCommandGroup(
                        new WristParallelCommand(),
                        new IntakeTransferCommand(),
                        new HorizontalSlidesRetractCommand()
                ),
                new ConditionalCommand(

                        new WaitCommand((int)(Robot.getInstance().horizontalSlides.getTicks()*.299401+50)),//true

                        new WaitCommand(500),
                        () -> Robot.getInstance().clawArm.state == ClawArm.STATE.INSPEC || Robot.getInstance().clawArm.state == ClawArm.STATE.PARK || Robot.getInstance().horizontalSlides.loc == HorizontalSlides.LOC.EXTENDED
                ),
                new VertSlidesStartCommand() //may need to swap
        );
    }

}
