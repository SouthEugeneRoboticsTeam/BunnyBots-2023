package org.sert2521.bunnybots2023.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.sert2521.bunnybots2023.ElectronicIDs

object Flywheel : SubsystemBase() {
    val flywheelMotor = CANSparkMax(ElectronicIDs.flywheelMotorID, CANSparkMaxLowLevel.MotorType.kBrushless)

    //this needs to be in the command
    //val feedForward = SimpleMotorFeedforward(TunedConstants.flywheelS, TunedConstants.flywheelV)
    init{
        flywheelMotor.setIdleMode(CANSparkMax.IdleMode.kCoast)
    }
    fun setVoltage(voltage:Double){
        flywheelMotor.setVoltage(voltage)
    }
    fun stop(){
        flywheelMotor.stopMotor()
    }
}