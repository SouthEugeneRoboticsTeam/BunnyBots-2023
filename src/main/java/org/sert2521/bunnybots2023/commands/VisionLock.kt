package org.sert2521.bunnybots2023.commands

import edu.wpi.first.math.MathUtil.clamp
import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.ConfigConstants
import org.sert2521.bunnybots2023.Input
import org.sert2521.bunnybots2023.RuntimeConstants
import org.sert2521.bunnybots2023.TunedConstants
import org.sert2521.bunnybots2023.subsystems.Vision
import kotlin.math.abs
import kotlin.math.absoluteValue

class VisionLock : CommandBase() {

    var lastYaw = 0.0
    var visionLostTargetCycles = 0
    val visionPID = PIDController(TunedConstants.visionP, TunedConstants.visionI, TunedConstants.visionD)

    override fun initialize() {
        RuntimeConstants.disableRightStick = true
        Vision.switchLights(true)
    }

    override fun execute() {
        when (Vision.hasTarget()){
            //if true then reset cycles
            true -> visionLostTargetCycles = 0

            //otherwise it increments its lost cycles
            false -> visionLostTargetCycles += 1
        }

        if (Vision.hasTarget()){
            if (Vision.getYaw().absoluteValue >= ConfigConstants.visionSusness){
                RuntimeConstants.visionRightStick = visionPID.calculate(Vision.getYaw())
            } else {
                RuntimeConstants.visionRightStick = 0.0
            }
            lastYaw = Vision.getYaw()
        } else {
            RuntimeConstants.visionRightStick = 0.0
            lastYaw = 0.0
        }




    }

    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return visionLostTargetCycles >= 10 || !(Input.visionAlignRev.asBoolean || Input.visionRenew.asBoolean)
    }

    override fun end(interrupted: Boolean) {
        RuntimeConstants.disableRightStick = false
    }
}
