package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmInitCommand;
import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmInspecTransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristInSpecTransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristTransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.horizSlides.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.turret.TurretInitCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.subsystems.outtake.arm.ClawArm;
import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

/*
resets all subsystems to how they were in initialization state
 */
public class ResetCommand extends SequentialCommandGroup {
    public ResetCommand(){
        super (

                new OuttakeClawOpenCommand(),
                new ConditionalCommand(
                        new SequentialCommandGroup(
                                new ClawArmInspecTransferCommand(),
                                new ClawWristInSpecTransferCommand(),
                                new WaitCommand(200),
                                new ClawArmInitCommand(),
                                new WaitCommand(300),
                                new ClawWristTransferCommand()

                        ),//true

                        new SequentialCommandGroup(
                                new ClawWristTransferCommand(),
                                new ClawArmInitCommand()
                        ),//false
                        () -> Robot.getInstance().clawArm.state == ClawArm.STATE.INSPEC
                ),
                new HorizontalSlidesRetractCommand(),
                new TurretInitCommand(),
                new WristParallelCommand(),
                new OuttakeClawOpenCommand(),

                new ConditionalCommand(
                        new WaitCommand(1000),//true


                        new WaitCommand(50),//false

                        () -> Robot.getInstance().vs.getVScurrRightPos() < 500
                ),
                new ClawWristTransferCommand(),
                new VertSlidesStartCommand() //may need to swap
        );
    }

}
