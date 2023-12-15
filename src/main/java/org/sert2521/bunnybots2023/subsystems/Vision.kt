package org.sert2521.bunnybots2023.subsystems

import edu.wpi.first.wpilibj.DigitalOutput
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.photonvision.PhotonCamera
import org.photonvision.targeting.PhotonPipelineResult
import org.photonvision.targeting.PhotonTrackedTarget
import org.sert2521.bunnybots2023.ElectronicIDs

object Vision : SubsystemBase() {
    //Maddie said to call it Fredrick
    val cam = PhotonCamera("Skynet")
    var result:PhotonPipelineResult = cam.latestResult


    val lights = DigitalOutput(ElectronicIDs.limelightID)

    //goal is to see ALL reflective tape, or at least the bigger ones
    private var targets:List<PhotonTrackedTarget> = result.targets

    //bestTarget means the biggest tape on the screen, so the closest
    private var bestTarget:PhotonTrackedTarget? = null

    override fun periodic(){
        result = cam.latestResult
        targets = result.targets

        //If targets is empty, then bestTarget is set to null
        if (!targets.isEmpty()){
            for (target in targets){
                //If there is no target yet, then this one is obviously the biggest
                if (bestTarget == null){
                    bestTarget = target

                //Else, if the target is bigger than the biggest yet, it becomes the new biggest
                } else if (target.area >= target.area){
                    bestTarget = target
                }
                //Otherwise target is smaller than bestTarget and so is discarded
            }

        } else {
            bestTarget = null
        }
    }

    fun hasTarget():Boolean{
        return bestTarget != null
    }

    fun getBestTarget():PhotonTrackedTarget?{
        return bestTarget
    }

    fun getYaw():Double{
        return bestTarget!!.yaw
    }

    fun switchLights(enabled: Boolean){
        lights.set(enabled)
    }
}