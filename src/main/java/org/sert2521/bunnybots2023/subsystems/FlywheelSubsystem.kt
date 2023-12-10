package org.sert2521.bunnybots2023.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.math.controller.SimpleMotorFeedforward
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.sert2521.bunnybots2023.ElectronicIDs
import org.sert2521.bunnybots2023.TunedConstants

object FlywheelSubsystem : SubsystemBase() {
    val flywheelMotor = CANSparkMax(ElectronicIDs.flywheelMotorId, CANSparkMaxLowLevel.MotorType.kBrushless)

    //this needs to be in the command
    //val feedForward = SimpleMotorFeedforward(TunedConstants.flywheelS, TunedConstants.flywheelV)
    init{
        flywheelMotor.setIdleMode(CANSparkMax.IdleMode.kCoast)
    }
    fun setVoltage(voltage:Double){
        flywheelMotor.setVoltage(voltage:Double)
    }
    fun stop(){
        flywheelMotor.stopMotor()
    }
}