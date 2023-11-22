package org.sert2521.bunnybots2023.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.sert2521.bunnybots2023.ElectronicIDs

object Wrist : SubsystemBase() {
 val motor = CANSparkMax(ElectronicIDs.wristMotor, CANSparkMaxLowLevel.MotorType.kBrushless)

 val encoder = motor.encoder
 val motorSpeed = 0.0
 var currSpeed = 0.0

 fun setSpeed(speed:Double){
  motor.set(speed)
  currSpeed = speed
 }

 fun getSpeed() :Double{
  return currSpeed
 }

 fun stop(){
  motor.stopMotor()
  currSpeed = 0.0
 }
}
