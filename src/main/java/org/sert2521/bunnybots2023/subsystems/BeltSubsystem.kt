package org.sert2521.bunnybots2023.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import org.sert2521.bunnybots2023.ElectronicIds

object BeltSubsystem : SubsystemBase() {
    private val motor = CANSparkMax(ElectronicIds.BeltMotorId, CANSparkMaxLowLevel.MotorType.kBrushless)
    private val encoder = motor.encoder

    private var currentSpeed = 0.0

    fun setMotor(speed: Double) {
        motor.set(speed)
        currentSpeed = speed
    }

    fun stopMotor() {
        motor.stopMotor()
        currentSpeed = 0.0
    }

    fun getSpeed(): Double {
        return encoder.velocity //returns RPMs not -1.0 to 1.0

    }

}