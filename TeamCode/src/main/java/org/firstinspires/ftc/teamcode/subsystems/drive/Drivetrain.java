package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.util.Globals;
import org.firstinspires.ftc.teamcode.subsystems.util.GreenSubsystem;

public class Drivetrain extends SampleMecanumDrive implements GreenSubsystem, Subsystem {

    public double drivePower;
    public DrivePID pid;
    public double heading, headingVel;
    State state;
    public Pose2d pose, vel;
    public Vector2d xState, yState, headingState;

    enum State {
        IDLE,
        PID
    }
    public Drivetrain (HardwareMap hardwareMap) {
        super(hardwareMap);
        pid = new DrivePID();
        drivePower = 1; // used to be .5
        state = State.IDLE;

        xState = new Vector2d();
        yState = new Vector2d();
        headingState = new Vector2d();
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

    public void fieldCentricDrive(double x, double y, double rotate){
        double botHeading = getExternalHeading();

        Vector2d input = new Vector2d(x,y);
        Vector2d output;

        output = input.rotated(-botHeading);

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
    }

    @Override
    public void init() {

    }

    public void telemetry(Telemetry tele) {
        tele.addData("heading current pos ", heading);
        tele.addData("rotate pow ", pid.getRotate(heading));
        tele.addData("drive train power ", drivePower);
    }

    public void idle() {
        state = State.IDLE;
        fieldCentricDrive(0,0,0);
    }

    @Override
    public void update() {
        updatePID();
        heading = getRawExternalHeading();
        pose = getPoseEstimate();
        xState = new Vector2d(pose.getX(), vel.getX());
        yState = new Vector2d(pose.getY(), vel.getY());
        headingState = new Vector2d(heading, headingVel);
    }
}
