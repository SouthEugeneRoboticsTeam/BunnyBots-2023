package org.sert2521.bunnybots2023.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.subsystems.Vision

class VisionLock : CommandBase() {

    var target = Vision.getBestTarget()
    var visionLostTargetCycles = 0

    override fun initialize() {}

    override fun execute() {
        if (Vision.hasTarget()){
            visionLostTargetCycles = 0
        } else {
            visionLostTargetCycles += 1
        }

        if (visionLostTargetCycles >= 20){
            this.cancel()
        }

    }

    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false
    }

    override fun end(interrupted: Boolean) {}
}
