package org.sert2521.bunnybots2023.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import org.sert2521.bunnybots2023.ElectronicIDs
import org.sert2521.bunnybots2023.commands.IndexerIdleCommand



object BeltSubsystem : SubsystemBase() {

    private val beltMotor = CANSparkMax(ElectronicIDs.beltMotorID, CANSparkMaxLowLevel.MotorType.kBrushless) // ID = -1
    private val beltEncoder = beltMotor.encoder

    private val kickerMotor = CANSparkMax(ElectronicIDs.kickerMotorID, CANSparkMaxLowLevel.MotorType.kBrushless) // ID = -1
    private val kickerEncoder = kickerMotor.encoder


    private var beltSpeed = 0.0
    private var kickerSpeed = 0.0

    private var kickerTime = 0
    private var intakeTime = 0
    init{
        //this.defaultCommand = IndexerIdleCommand()
    }

    fun setBeltSpeed(speed: Double) {
        beltMotor.set(speed)
        beltSpeed = speed
    }
    fun setKickerSpeed(speed: Double) {
        kickerMotor.set(speed)
        kickerSpeed = speed
    }





// superfluous mumbo jumbo (do not delete though)
/*
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


 */
}