package org.sert2521.bunnybots2023.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj.DutyCycleEncoder
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.sert2521.bunnybots2023.ElectronicIDs
import org.sert2521.bunnybots2023.PhysicalConstants
import org.sert2521.bunnybots2023.commands.RunWrist
import java.beans.Encoder
import kotlin.math.PI

object Wrist : SubsystemBase() {
 val motor = CANSparkMax(ElectronicIDs.wristMotor, CANSparkMaxLowLevel.MotorType.kBrushless)

 val encoder = motor.encoder
 val trueEncoder = DutyCycleEncoder(ElectronicIDs.wristTrueEncoder)
 val motorSpeed = 0.0

 init{
  motor.setSmartCurrentLimit(40)
  defaultCommand= RunWrist()
  motor.inverted = true
 }
 fun setSpeed(speed:Double){
  motor.set(speed)
 }

 fun setVoltage(voltage:Double){
  motor.setVoltage(voltage)
 }

 fun getEncoder():Double{
  return trueEncoder.get()
 }

 fun getRadians():Double{
  var wristAngle = (trueEncoder.get())* PhysicalConstants.wristEncoderMultiplier+ PhysicalConstants.wristEncoderTransform

  if (wristAngle<-PI){
   wristAngle += 2* PI
  }

  return wristAngle
 }

 fun resetEncoder(){
  trueEncoder.reset()
 }

 fun stop(){
  motor.stopMotor()
 }
}
