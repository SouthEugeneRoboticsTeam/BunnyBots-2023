package org.sert2521.bunnybots2023.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj.RobotController
import edu.wpi.first.wpilibj2.command.SubsystemBase;

object IntakeSubsystem : SubsystemBase() {
    private val intakeMotor = CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless)

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