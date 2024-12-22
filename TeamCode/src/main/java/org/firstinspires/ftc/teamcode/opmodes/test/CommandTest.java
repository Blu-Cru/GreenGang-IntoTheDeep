package org.firstinspires.ftc.teamcode.opmodes.test;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.spec.GetSpecCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

@TeleOp(name = "command test", group = "test")
public class CommandTest extends GreenLinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        robot = Robot.getInstance().create(hardwareMap);

        addIntake();
        addOuttakeClaw();
        addClawArm();
        addClawWrist();

        robot.init();

        waitForStart();

        while(opModeIsActive()) {

            if (gamepad1.a)
                new GetSpecCommand().schedule();

            CommandScheduler.getInstance().run();
        }
    }
}
