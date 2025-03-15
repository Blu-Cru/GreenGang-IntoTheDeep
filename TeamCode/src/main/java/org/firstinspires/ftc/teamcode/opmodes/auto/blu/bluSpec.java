package org.firstinspires.ftc.teamcode.opmodes.auto.blu;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.spec.SpecIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecDunk;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "blue spec auto", group = "paths")
public class bluSpec extends GreenLinearOpMode {
    public static TrajectoryVelocityConstraint FAST_VEL = SampleMecanumDrive.getVelocityConstraint(46, Math.toRadians(220), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint NORMAL_VEL = SampleMecanumDrive.getVelocityConstraint(41, Math.toRadians(180), DriveConstants.TRACK_WIDTH);
    public static TrajectoryVelocityConstraint SLOW_VEL = SampleMecanumDrive.getVelocityConstraint(20, Math.toRadians(150), DriveConstants.TRACK_WIDTH);

    public static TrajectoryAccelerationConstraint FAST_ACCEL = SampleMecanumDrive.getAccelerationConstraint(42);
    public static TrajectoryAccelerationConstraint NORMAL_ACCEL = SampleMecanumDrive.getAccelerationConstraint(40);
    public static TrajectoryAccelerationConstraint SLOW_ACCEL = SampleMecanumDrive.getAccelerationConstraint(25);

    public static TrajectoryVelocityConstraint[] velos = {SLOW_VEL, NORMAL_VEL, FAST_VEL};
    public static TrajectoryAccelerationConstraint[] accels = {SLOW_ACCEL, NORMAL_ACCEL, FAST_ACCEL};

    TrajectorySequence farBlue;
    SampleMecanumDrive mecDrive;
    boolean done;

    @Override
    public void initialize() {
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

        mecDrive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-24, 64, Math.toRadians(90));
        mecDrive.setPoseEstimate(startPose);

        farBlue = mecDrive.trajectorySequenceBuilder(startPose)

                .setTangent(Math.toRadians(-90))
                .setConstraints(NORMAL_VEL, NORMAL_ACCEL)

                //PRELOAD SCORE
                        .addTemporalMarker(() -> {
                            new OuttakeClawCloseCommand();
                            new WaitCommand(300);
                            new AutoSpecOuttake().schedule();
                        })

                .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(-90))
                        .addTemporalMarker(() -> {
                            new WaitCommand(2000).schedule();
                            new AutoSpecDunk().schedule();
                        })
                .waitSeconds(1)
                .setTangent(90)
                        .addTemporalMarker(() -> {
                            new WaitCommand(1000);
                            new ResetCommand().schedule();
                        })
                .setConstraints(SLOW_VEL,SLOW_ACCEL)
                .splineToSplineHeading(new Pose2d(-35,26,Math.toRadians(-90)), Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(-35, 16), Math.toRadians(-90))

                .splineToConstantHeading(new Vector2d(-47, 15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-47, 55), Math.toRadians(90))

                //FINISHED PUSHING FIRST SAMPLE TO OBSERVATION ZONE

                .splineToConstantHeading(new Vector2d(-47, 16), Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(-60, 16), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-60, 55), Math.toRadians(90))
                //FINISHED PUSHING SECOND SAMPLE TO OBSERVATION ZONE
                .splineToConstantHeading(new Vector2d(-60, 16), Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(-63, 16), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-63, 55), Math.toRadians(90))
                .setConstraints(FAST_VEL,FAST_ACCEL)
                //FINISHED PUSHING THIRD SAMPLE TO OBSERVATION ZONE
                //FIRST SPECIMEN INTAKE
                        .addTemporalMarker(() -> {
                            new SpecIntakeCommand().schedule();
                        })
                .splineToConstantHeading(new Vector2d(-48, 52), Math.toRadians(90))
                        .addTemporalMarker(() -> {
                            new OuttakeClawCloseCommand().schedule();
                        })
                .waitSeconds(2.0)

                .setTangent(-45)
                //FIRST SPECIMEN SCORE
                        .addTemporalMarker(() -> {
                            new WaitCommand(500).schedule();
                            new AutoSpecOuttake().schedule();
                        })

                .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(-90))
                        .addTemporalMarker(() -> {
                            new WaitCommand(2000).schedule();
                            new AutoSpecDunk().schedule();
                        })

                //SECOND SPECIMEN INTAKE
                .waitSeconds(1)
                        .addTemporalMarker(() -> {
                            new WaitCommand(500).schedule();
                            new SpecIntakeCommand().schedule();
                        })
                .setTangent(135)
                .splineToSplineHeading(new Pose2d(-48,52,Math.toRadians(-90)), Math.toRadians(90))

                        .addTemporalMarker(() -> {
                            new OuttakeClawCloseCommand().schedule();
                        })
                .waitSeconds(2.0)

                .setTangent(-45)
                //SECOND SPECIMEN SCORE
                        .addTemporalMarker(() -> {
                            new WaitCommand(500).schedule();
                            new AutoSpecOuttake().schedule();
                        })

                .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(-90))
                        .addTemporalMarker(() -> {
                            new WaitCommand(2000).schedule();
                            new AutoSpecDunk().schedule();
                        })

                //THIRD SPECIMEN INTAKE
                .waitSeconds(1)
                        .addTemporalMarker(() -> {
                            new WaitCommand(500).schedule();
                            new SpecIntakeCommand().schedule();
                        })
                .setTangent(135)
                .splineToSplineHeading(new Pose2d(-48,52,Math.toRadians(-90)), Math.toRadians(90))
                        .addTemporalMarker(() -> {
                            new OuttakeClawCloseCommand().schedule();
                        })
                .waitSeconds(2.0)

                .setTangent(-45)
                //THIRD SPECIMEN SCORE
                        .addTemporalMarker(() -> {
                            new WaitCommand(500).schedule();
                            new AutoSpecOuttake().schedule();
                        })

                .splineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(90)), Math.toRadians(-90))
                        .addTemporalMarker(() -> {
                            new WaitCommand(1000).schedule();
                            new AutoSpecDunk().schedule();
                        })
                .waitSeconds(1)
                        .addTemporalMarker(() -> {
                            new WaitCommand(500).schedule();
                            new ResetCommand().schedule();
                        })
                .setTangent(135)
                .strafeTo(new Vector2d(-48,60))


                .build();

        outtakeClaw.close();
    }

    @Override
    public void periodic() {


        if (!drivetrain.isBusy() && !done){
            mecDrive.followTrajectorySequenceAsync(farBlue);
            done = true;
        }
        try {
            mecDrive.updateTrajectory();
        } catch (Exception e){
//            requestOpModeStop();
        }
    }


}
