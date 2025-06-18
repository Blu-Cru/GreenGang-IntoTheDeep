package org.firstinspires.ftc.teamcode.greengang.common.subsystems.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.drive.TwoWheelTrackingLocalizer;
import org.firstinspires.ftc.teamcode.greengang.common.util.Globals;
import org.firstinspires.ftc.teamcode.greengang.common.util.GreenSubsystem;

public class Drivetrain extends SampleMecanumDrive implements GreenSubsystem, Subsystem {

    public double drivePower;
    public PinpointLocalizer ppl;
    public DrivePID pid;
    public double heading;
    State state;
    public Pose2d pose;
    public Vector2d headingState = new Vector2d(0, 0);

    boolean lastTurning, lastTranslating;
    enum State {
        IDLE,
        PID
    }
    public Drivetrain (HardwareMap hardwareMap) {
        super(hardwareMap);
        pid = new DrivePID();
        drivePower = 1;
        state = State.IDLE;
        lastTranslating=false;
        lastTurning=false;
        ppl = new PinpointLocalizer(hardwareMap);
        setLocalizer(ppl);
    }

    public void setDrivePower(double drivePower) {
        drivePower = Globals.correctPower(drivePower);
    }

    public void updatePID() {
        pid.updatePID();
    }
    public void pidTo(Pose2d targetPose) {
        state = State.PID;
        pid.setTargetPose(targetPose);
    }

    public void drive(double x, double y, double rotate) {
        Vector2d input = new Vector2d(x,y);
        Vector2d output = input.rotated(Math.toRadians(-90));

        x = output.getX();
        y = output.getY();

        setWeightedDrivePower(new Pose2d(x * drivePower, y * drivePower, rotate * drivePower));
    }

    public void fieldCentricDrive(Pose2d drivePose){
        fieldCentricDrive(drivePose.getX(), drivePose.getY(), drivePose.getHeading());
    }
    public void fieldCentricDrive(Vector2d translation, double rotation) {
        fieldCentricDrive(new Pose2d(translation, rotation));
    }

    public void teleOpDrive(Gamepad g1) {
        state = State.IDLE;

        double vert = -g1.left_stick_y;
        double horiz = g1.left_stick_x;
        double rotate = -g1.right_stick_x * 0.8;

        boolean translating = Math.abs(vert) > 0.05 || Math.abs(horiz) > 0.05;
        boolean turning = Math.abs(rotate) > 0.05;

        if(turning) {
            // drive normally
            fieldCentricDrive(new Vector2d(horiz, vert).times(drivePower), rotate * drivePower);
        } else if(lastTurning) {
            // if just turning, turn to new heading
            pid.headingController.reset();
            driveToHeading(horiz, vert);
        } else if(translating && !lastTranslating) {
            // if just started translating, drive to current heading
            driveToHeading(horiz, vert, heading);
        } else if(!translating) {
            pid.headingController.reset();
            pid.setTargetHeading(heading);
            // if not translating, drive 0,0,0
            drive(0,0,0);
        } else {
            // if translating and not turning, drive to target heading
            driveToHeading(horiz, vert);
        }

        lastTurning = turning;
        lastTranslating = translating;
    }

    public void fieldCentricDrive(double x, double y, double rotate){
        Vector2d input = new Vector2d(x,y);
        Vector2d output;

        output = input.rotated(-heading);

        x = output.getX();
        y = output.getY();

        setWeightedDrivePower(new Pose2d(x * drivePower, y * drivePower, rotate * drivePower));
    }

    public void driveToHeading(double x, double y, double targetHeading) {
        pid.setTargetHeading(targetHeading);
        driveToHeading(x,y);
    }

    public void driveToHeading(double x, double y) {
        if(Globals.fieldCentric) {
            fieldCentricDrive(x*drivePower, y*drivePower, pid.getRotate(this));
        }else {
            drive(x*drivePower, y*drivePower, heading*drivePower);
        }
    }

    public void setHeading(double heading){
        this.heading = heading;
        ppl.setHeading(heading);
    }

    @Override
    public void init() {

    }

    public void telemetry(Telemetry tele) {
        tele.addData("heading current pos ", heading);
//        tele.addData("rotate pow ", pid.getRotate(heading));
        tele.addData("drive train power ", drivePower);
    }

    public void idle() {
        state = State.IDLE;
        fieldCentricDrive(0,0,0);
    }

    @Override
    public void update() {
        updatePID();
        heading = ppl.getHeading();
        pose = ppl.getPoseEstimate();
        headingState = new Vector2d(heading, ppl.getHeadingVelocity());
//        if (pose != null && vel != null) {
//            xState = new Vector2d(pose.getX(), vel.getX());
//            yState = new Vector2d(pose.getY(), vel.getY());
//        } else {
//            xState = new Vector2d(0,0);
//            yState = new Vector2d(0,0);
//        }
        ppl.update();
    }
}
