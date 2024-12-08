package org.firstinspires.ftc.teamcode.subsystems.intake.arm;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

@Config
public class IntakeArm implements Subsystem {
    public DcMotorEx armRotate;
    public static double armP = 0.015, armI = 0, armD = 0.0002; // tuned 0.0175
    private final PIDController armRotatePID;
    public double armRotateTargetPos;
    public double armRotatePower;

    public static double
        VERTICAL_POS = 0.2484,
        DOWN_POS = 0.96,
        INTAKE_POS =2.6003,
        INIT = 0;


    public IntakeArm(HardwareMap hardwareMap) {
        armRotate = hardwareMap.get(DcMotorEx.class, "armRotate");
        armRotatePID = new PIDController(armP, armI, armD);
        armRotateTargetPos = 0;
        armRotatePower = 0;
    }

    public void init() {
        armRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRotate.setPower(0); // check what power it should be
        armRotate.setDirection(DcMotorSimple.Direction.REVERSE);
        armRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void parallel(){
        pidTo(radToTick(DOWN_POS));
    }
    public void intake(){
        pidTo(radToTick(INTAKE_POS));
    }
    public void rest() {
        pidTo(radToTick(VERTICAL_POS));
    }

    public void transfer() {
        pidTo(radToTick(INIT));
    }

    public double getAngle(double armPos) {
        return armPos * 1/3895.9 * (2*Math.PI);
    }

    public void update() {
        //double armRotateCurrentPos = this.getArmRotatePosition();

        armRotatePower = Range.clip(armRotatePID.calculate(armRotate.getCurrentPosition()), -0.6, 0.75);
        double armRotateError = armRotate.getCurrentPosition() - armRotatePID.getSetPoint();

        if(Math.abs(armRotateError) < 0.08 && armRotate.getCurrentPosition() < 0.1) {
            stopArmRotate();
        } else {
            setArmRotatePower(armRotatePower);
        }
    }

    public double radToTick(double target){
        return target * 3895.9/(Math.PI*2);
    }
    public void stopArmRotate() {
        armRotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armRotate.setPower(0);
    }
    public double getArmRotatePosition() {
        return getAngle(armRotate.getCurrentPosition());
    }

    public void autoArmRotate(double power, int targetPos) {
        armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotatePower = power;
        armRotate.setPower(power);
        armRotate.setTargetPosition(targetPos);
    }

    public void pidTo(double targetPos) {
        armRotatePID.setSetPoint(targetPos);
    }

    public void setArmRotatePower(double power) {
        armRotatePower = power;
        armRotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armRotate.setPower(power);
    }
    public void updatePID() {
        armRotatePID.setPID(armP, armI, armD);
    }

    public String telemetry(Telemetry telemetry) {
        telemetry.addData("ARM CURRENT POS: ", this.getArmRotatePosition());
        telemetry.addData("ARM POWER: ", armRotatePower);
        return null;
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

}
