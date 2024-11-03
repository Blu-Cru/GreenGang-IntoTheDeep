package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Alliance;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;
import org.firstinspires.ftc.teamcode.subsystems.drive.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeColorSensor;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.transfer.Transfer;

@TeleOp(name="Solo", group ="TeleOp")
public class Solo extends GreenLinearOpMode {

    Intake intake;
    IntakeColorSensor color;
    // Transfer claw;
    Alliance alliance;
    Drivetrain dt;
    double y, x, rx;
    Drive drive;

    Robot bot;
    @Override
    public void runOpMode() throws InterruptedException {
        intake = new Intake(hardwareMap);
        color = new IntakeColorSensor(hardwareMap);
        // claw = new Transfer(hardwareMap);
        dt = new Drivetrain(hardwareMap);

        alliance = Alliance.BLUE;
        drive = Drive.FIELDCENTRIC;

        while(opModeInInit()) {

            intake.init();
            color.init();
            color.startReading();
            dt.init();

            // claw.init();

            if(gamepad1.x) {
                alliance = alliance.flip();
            }

            if (gamepad1.y){
                drive = drive.flip();
            }

            telemetry.addData("ALLIANCE: ", alliance);
            telemetry.addData("DRIVE TYPE: ", drive);
            telemetry.update();
        }

        waitForStart();

        while(opModeIsActive()) {

            color.read();
            driveControl();
            drive(drive);

            if (gamepad1.left_bumper && !color.isFull()) {
                intake.intakeSetPower(1);
            } else if (gamepad1.right_bumper) {
                intake.intakeSetPower(-1);
            } else {
                intake.intakeSetPower(0);
            }

            if (color.isFull())
                spit(color, intake, alliance);

            // bot.telemetry(telemetry);

            telemetry.addData("SLOT ", color.slotState);
            telemetry.update();
        }
    }

    public void spit(IntakeColorSensor color, Intake intake, Alliance alliance){
        switch(alliance) {
            case RED:
                if (color.slotState.equals(IntakeColorSensor.SlotState.BLUE)) {
                    intake.intakeSetPower(-1);
                }

            case BLUE:
                if (color.slotState.equals(IntakeColorSensor.SlotState.RED)) {
                    intake.intakeSetPower(-1);
                }
        }
    }

    public void drive(Drive drive){
        switch(drive) {
            case FIELDCENTRIC:

                if (gamepad1.options) {
                    dt.setExternalHeading(Math.toRadians(90));
                }
                dt.fieldCentricDrive(x, y, rx);

            case ROBOTCENTRIC:
                dt.drive(x, y, rx);
        }
    }

    public void driveControl(){
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = -gamepad1.right_stick_x;

        if(gamepad1.right_trigger > 0.4) {
            Drivetrain.drivePower = 0.3;
        }
        else {
            Drivetrain.drivePower = 0.6;
        }
    }
}
