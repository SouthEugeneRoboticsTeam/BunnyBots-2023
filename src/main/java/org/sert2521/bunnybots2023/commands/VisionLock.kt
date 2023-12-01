package org.sert2521.bunnybots2023.commands

import edu.wpi.first.math.MathUtil.clamp
import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.RuntimeConstants
import org.sert2521.bunnybots2023.TunedConstants
import org.sert2521.bunnybots2023.subsystems.Vision
import kotlin.math.abs

class VisionLock : CommandBase() {

    var target = Vision.getBestTarget()
    var visionLostTargetCycles = 0

    override fun initialize() {
        RuntimeConstants.disableRightStick = true

    }

    override fun execute() {
        when (Vision.hasTarget()){
            //if true then reset cycles
            true -> visionLostTargetCycles = 0

            //otherwise it increments its lost cycles
            false -> visionLostTargetCycles += 1
        }

        if (Vision.hasTarget() && abs(Vision.getYaw())>=TunedConstants.visionSusness){
            RuntimeConstants.visionRightStick = clamp((Vision.getYaw()*TunedConstants.visionP), -1.0, 1.0)
        } else {
            RuntimeConstants.visionRightStick = 0.0
        }

        //If vision lost for more than a 1/5 seconds, then it panic cancels
        if (visionLostTargetCycles >= 10){
            this.cancel()
        }
    }

    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return visionLostTargetCycles >= 10
    }

    override fun end(interrupted: Boolean) {}
}
