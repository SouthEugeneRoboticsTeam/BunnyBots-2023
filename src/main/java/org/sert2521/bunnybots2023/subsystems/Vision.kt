package org.sert2521.bunnybots2023.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.PhotonCamera
import org.photonvision.targeting.PhotonPipelineResult
import org.photonvision.targeting.PhotonTrackedTarget

object Vision : SubsystemBase() {
    val cam = PhotonCamera("Camera")
    var result:PhotonPipelineResult? = null
    var targets:List<PhotonTrackedTarget>? = null

    override fun periodic(){
        result = cam.latestResult
        targets = result.getTargets()
    }

}