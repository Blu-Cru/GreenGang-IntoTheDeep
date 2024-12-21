package org.firstinspires.ftc.teamcode.opmodes.test;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.TransferIntoClawCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;
import org.firstinspires.ftc.teamcode.subsystems.intake.intake.IntakeColorSensor;

@TeleOp(name = "command test", group = "test")
public class CommandTest extends GreenLinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        robot = Robot.getInstance().create(hardwareMap);

        addIntake();

        robot.init();

        waitForStart();

        while(opModeIsActive()) {

            if (gamepad1.right_bumper)
                new OuttakeClawCloseCommand().schedule();

            CommandScheduler.getInstance().run();
        }
    }
}
