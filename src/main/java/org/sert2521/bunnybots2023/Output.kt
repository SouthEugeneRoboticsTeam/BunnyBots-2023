package org.sert2521.bunnybots2023

import edu.wpi.first.util.sendable.Sendable
import edu.wpi.first.util.sendable.SendableBuilder
import edu.wpi.first.wpilibj.DataLogManager
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.smartdashboard.Field2d
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.sert2521.bunnybots2023.subsystems.Drivetrain
import org.sert2521.bunnybots2023.subsystems.Vision
import org.sert2521.bunnybots2023.subsystems.Wrist
import java.io.File

object Output : SubsystemBase() {
 private val values = mutableListOf<Pair<String, () -> Double>>()
 private val bools = mutableListOf<Pair<String, () -> Boolean>>()
 private val field = Field2d()
 private val visionField = Field2d()

 init {


  val storageDevices = File("/media").listFiles()
  if (storageDevices != null) {
   if (storageDevices.isNotEmpty()) {
    DataLogManager.start(storageDevices[0].absolutePath)
    DriverStation.startDataLog(DataLogManager.getLog())
   }
  }
  update()
  values.add(Pair("Wrist Radians") { Wrist.getRadians() })
  values.add(Pair("Wrist Encoder") { Wrist.getEncoder() })
 }

 fun update(){
  for (value in values) {
   SmartDashboard.putNumber("Output/${value.first}", value.second())
  }

  for (bool in bools) {
   SmartDashboard.putBoolean("Output/${bool.first}", bool.second())
  }

 }





}