package org.sert2521.bunnybots2023.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.sert2521.bunnybots2023.ElectronicIDs
import org.sert2521.bunnybots2023.commands.ClawIntake

object Claw : SubsystemBase() {
    private val clawMotor = CANSparkMax(ElectronicIDs.clawMotorId, CANSparkMaxLowLevel.MotorType.kBrushless)


    init {
        clawMotor.inverted = true
        clawMotor.idleMode = CANSparkMax.IdleMode.kBrake
        clawMotor.setSmartCurrentLimit(10, 45)
        //this.defaultCommand = ClawIntake(0.1)
    }

    fun setMotor(speed: Double) {
        clawMotor.set(speed)
    }

    fun stop() {
        clawMotor.stopMotor()
    }
}