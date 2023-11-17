package org.sert2521.bunnybots2023.subsystems

import com.revrobotics.CANSparkMax
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.sert2521.bunnybots2023.ElectronicIDs

object Outtake : SubsystemBase() {

    private val princess = CANSparkMax(ElectronicIDs.outtakemotorID)
//princess is the motor. dont change it istg.

    private var currentSpeed = 0.0

    init{
        princess.idleMode = CANSparkMax.IdleMode.kCoast
    }
    fun setMotor(speed:Double){
        princess.set(speed)
        currentSpeed = speed

    }
    fun stop(){
        princess.stopMotor()
        currentSpeed = 0.0
    }

}