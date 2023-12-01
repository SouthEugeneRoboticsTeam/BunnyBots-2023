package org.sert2521.bunnybots2023.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.sert2521.bunnybots2023.ElectronicIDs

object Intake : SubsystemBase() {
    private val intakeMotor = CANSparkMax(ElectronicIDs.intakeMotorId, CANSparkMaxLowLevel.MotorType.kBrushless)

    init {
        intakeMotor.idleMode = CANSparkMax.IdleMode.kBrake
        intakeMotor.setSmartCurrentLimit(10, 45)
    }

    fun setMotor(speed: Double) {
        intakeMotor.set(speed)
    }

    fun stop() {
        intakeMotor.stopMotor()
    }
}