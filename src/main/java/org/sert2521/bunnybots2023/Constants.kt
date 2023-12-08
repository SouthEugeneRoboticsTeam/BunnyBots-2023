package org.sert2521.bunnybots2023

import edu.wpi.first.math.MatBuilder
import edu.wpi.first.math.Matrix
import edu.wpi.first.math.Nat
import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.numbers.N1
import edu.wpi.first.math.numbers.N3
import kotlin.math.PI

/*
 * The Constants file provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This file should not be used for any other purpose.
 * All String, Boolean, and numeric (Int, Long, Float, Double) constants should use
 * `const` definitions. Other constant types should use `val` definitions.
 */

class SwerveModuleData(val position: Translation2d, val powerMotorID: Int, val angleMotorID: Int, val angleEncoderID: Int, val angleOffset: Double, val inverted: Boolean, val invertedRotation: Boolean)

object PhysicalConstants {
    //Measurement is when robot is 28 inches without bumpers
    const val halfSideLength = 0.286378246381


    const val powerEncoderMultiplierPosition = PI * 0.1016 / 8.14
    const val powerEncoderMultiplierVelocity = powerEncoderMultiplierPosition / 60.0
    const val angleEncoderMultiplier = 0.01770398088

    const val wristEncoderMultiplier = -2.859/(0.54)

    const val wristEncoderTransform = -0.176524
    //0.5298-0.08384

    const val wristSetpointGround = PI/16.0
    const val wristSetpointTote = PI/4.0
    const val wristSetpointRest = PI/2.0

    const val wristSetpointMin = PI/16
    const val wristSetpointMax = PI/2.0

}

object TunedConstants {
    //I think this is filtering
    val encoderDeviations: Matrix<N3, N1> = MatBuilder(Nat.N3(), Nat.N1()).fill(1.0, 1.0, 0.01)
    val defaultVisionDeviations: Matrix<N3, N1> = MatBuilder(Nat.N3(), Nat.N1()).fill(1.0, 1.0, 1000.0)


    //Feedforward constants
    const val swervePowerS = 8.2
    const val swervePowerV = 7.0
    const val swervePowerA = 0.0

    //PID loop constants
    const val swervePowerP = 9.0
    const val swervePowerI = 0.0
    const val swervePowerD = 0.0

    const val swerveAngleP = 7.8
    const val swerveAngleI = 0.0
    const val swerveAngleD = 0.0

    const val swerveAngleS = 0.0

    const val wristP = 0.0
    const val wristI = 0.0
    const val wristD = 0.0

    //ESTIMATIONS
    const val wristS = 16.0
    const val wristG = 0.0//0.31
    const val wristV = 0.0//1.30
    const val wristA = 0.0//0.01
}

object ConfigConstants {

    const val drivetrainOptimized = true
    //Controller constants
    const val powerDeadband = 0.075
    const val rotDeadband = 0.075

    //Drive speed constants
    const val driveSpeed = 7.0
    const val driveSecondarySpeed = 2.75
    const val rotSpeed = 10.0
    const val rotSecondarySpeed = 2.75

    //Acceleration of drivetrain
    const val driveSpeedupChangeSpeed = 30.0
    const val driveSlowdownChangeSpeed = 36.0

    const val wristSlideSpeed = PI/200
}

object ElectronicIDs {
    const val clawMotorId = 9
    val swerveModuleData = listOf(//-68.922, (-67.6923+245.1435)x=pi
        SwerveModuleData(Translation2d(-PhysicalConstants.halfSideLength, PhysicalConstants.halfSideLength), 13, 3, 17, 5.458-PI/2, true, false), //Front Right
        SwerveModuleData(Translation2d(-PhysicalConstants.halfSideLength, -PhysicalConstants.halfSideLength), 10, 5, 16, 1.23-PI/2, true, false), //Back Right
        SwerveModuleData(Translation2d(PhysicalConstants.halfSideLength, -PhysicalConstants.halfSideLength), 12, 7, 15, 4.4785-PI/2, false, false), //Back Left
        SwerveModuleData(Translation2d(PhysicalConstants.halfSideLength, PhysicalConstants.halfSideLength), 11, 8, 14, 6.29-PI/2, false, false) //Front Left
    )

    const val wristMotor = 1
    const val wristTrueEncoder = 8
}

object RuntimeConstants {
    var wristSetPoint = 0.0

}



