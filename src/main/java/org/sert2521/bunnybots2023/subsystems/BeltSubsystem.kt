package org.sert2521.bunnybots2023.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import org.sert2521.bunnybots2023.ElectronicIds


//Belt system is the belt and the kicker
object BeltSubsystem : SubsystemBase() {
    /*
    modes:
    - kicker slow reverse
    - kicker fast forward
    - belt slow
    - belt fast
     */
    private val beltMotor = CANSparkMax(ElectronicIds.BeltMotorId, CANSparkMaxLowLevel.MotorType.kBrushless) // ID = -1
    private val beltEncoder = beltMotor.encoder

    private val kickerMotor = CANSparkMax(ElectronicIds.KickerMotorId, CANSparkMaxLowLevel.MotorType.kBrushless) // ID = -1
    private val kickerEncoder = kickerMotor.encoder

    private var currentSpeed = 0.0

    fun setBeltSpeed(speed: Double) {
        beltMotor.set(speed)
    }
    fun setkickerSpeed(speed: Double) {
        kickerMotor.set(speed)
    }
    private fun setBeltSpeedRPM(rpm: Double) {
        val ratedSpeedRPM = 5676
        val speedPerUnit = rpm / ratedSpeedRPM

        when {
            speedPerUnit > 1.0 -> beltMotor.set(1.0)
            speedPerUnit < -1.0 -> beltMotor.set(-1.0)
            else -> beltMotor.set(speedPerUnit)
        }
    }
    private fun setKickerSpeedRPM(rpm: Double) {
        val ratedSpeedRPM = 5676
        val speedPerUnit = rpm / ratedSpeedRPM

        // Prevent exceeding -1.0 .. 1.0 limit
        when {
            speedPerUnit > 1.0 -> kickerMotor.set(1.0)
            speedPerUnit < -1.0 -> kickerMotor.set(-1.0)
            else -> kickerMotor.set(speedPerUnit)
        }


    }

    fun kickerKick() {
        // Needs timing to be calibrated
        kickerMotor.set(-0.7)
        Thread.sleep(700) // 700 milliseconds
        kickerSpeedSlow()
    }
    private fun kickerSpeedSlow() {
        kickerMotor.set(0.1)
    }

    private fun beltSpeedSlow() {
        beltMotor.set(0.1)
    }

    private fun beltSpeedFast() {
        beltMotor.set(0.7)
    }


    private fun getSpeedBeltRPM(): Double {
        val ratedSpeedRPM = 5767
        val velocityRPM = beltEncoder.velocity * ratedSpeedRPM
        return velocityRPM
    }

    private fun getSpeedKickerRPM(): Double {
        val ratedSpeedRPM = 5767
        val velocityRPM = kickerEncoder.velocity * ratedSpeedRPM
        return velocityRPM
    }

}