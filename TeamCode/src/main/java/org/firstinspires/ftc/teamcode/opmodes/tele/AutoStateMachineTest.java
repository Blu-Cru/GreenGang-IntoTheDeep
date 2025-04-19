package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sfdev.assembly.state.StateMachine;
import com.sfdev.assembly.state.StateMachineBuilder;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.common.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.common.commands.intake.RetractAutoCommand;
import org.firstinspires.ftc.teamcode.common.commands.spec.auto.AutoSpecDunk;
import org.firstinspires.ftc.teamcode.common.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.common.subsystems.drive.DriveMode;

//todo: add path implementation
@TeleOp(name="AutoStateMachineTest", group ="TeleOp")

public class AutoStateMachineTest extends GreenLinearOpMode{

    double y, x, rx;
    enum State{
        INTAKING_SAMPLE,
        HIGH_BUCKET,
        ABOVE_SPEC,
        SPEC_DUNK,
        TRANSFER,
        SCORED
    }
    StateMachine sm;
    public void initialize(){

        addDrivetrain();
        addIntake();
        addStickyG1();
        addClawArm();
        addOuttakeClaw();
        addHorizontalSlides();
        addIntakeWrist();
        addClawWrist();
        addVertSlides();
        addHang();
        addIntakeColorSensor();

        sm = new StateMachineBuilder()

                // Sample auto; Intaking a sample
                // TODO: add a timer so it auto retracts after ~4sec if empty & goes to sample 2 loc
                .state(AutoStateMachineTest.State.INTAKING_SAMPLE)
                .transition(()-> robot.color.isFull(), State.HIGH_BUCKET, ()-> {
                    new RetractAutoCommand().schedule();
                })

                // Sample auto; scoring high bucket
                .state(State.HIGH_BUCKET)
                .onEnter(()-> {
                    new ScoringHighBucketCommand().schedule();
                })
                .transition(()-> robot.outtakeClaw.isOpen(), State.SCORED, ()-> {
                    new WaitCommand(100).schedule();
                    new ResetCommand().schedule();
                })

                .state(State.SPEC_DUNK)
                .onEnter(()-> {
                    new AutoSpecDunk().schedule();
                })
                .transition(()-> !robot.outtakeClaw.isOpen(), State.SCORED, ()-> {
                    new WaitCommand(100).schedule();
                    new ResetCommand().schedule();
                })

                .state(State.TRANSFER)
                .onEnter(()-> {
                    new TransferCommand().schedule();
                })

                .build();
        sm.setState(State.HIGH_BUCKET);
        sm.start();
    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("Robot State: ", sm.getState());
    }

    @Override
    public void periodic(){
        driveControl();
        drive(driveMode);
        y = gamepad1.left_stick_y;
        x = -gamepad1.left_stick_x;
        rx = -gamepad1.right_stick_x;

        //Robot moves slower
        if(gamepad1.right_trigger > 0.4) {
            drivetrain.drivePower = 0.3;
        }
        else {
            drivetrain.drivePower = 0.6;
        }

        sm.update();
        telemetry.update();
    }
    public void drive(DriveMode driveMode) {
        switch (driveMode) {
            case FIELDCENTRIC:
                if (stickyG1.left_stick_button) {
                    gamepad1.rumble(200);
                    drivetrain.setExternalHeading(Math.toRadians(90));
                }
                drivetrain.fieldCentricDrive(x, y, rx);
                break;
            case ROBOTCENTRIC:
                drivetrain.drive(x, y, rx);
                break;
        }
    }

    public void driveControl() {
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = -gamepad1.right_stick_x;

        if (gamepad1.right_trigger > 0.4) {
            drivetrain.drivePower = 0.3;
        } else {
            drivetrain.drivePower = 0.6;
        }
    }
}
