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
        if (Vision.hasTarget()){
            visionLostTargetCycles = 0

        } else {
            visionLostTargetCycles += 1
        }

        if (visionLostTargetCycles >= 10){
            this.cancel()
        }

        if (Vision.hasTarget() && abs(Vision.getYaw())>=TunedConstants.visionSusness){
            RuntimeConstants.visionRightStick = clamp((Vision.getYaw()*TunedConstants.visionP), -1.0, 1.0)
        } else {
            RuntimeConstants.visionRightStick = 0.0
        }
    }

    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false
    }

    override fun end(interrupted: Boolean) {}
}
