package org.sert2521.bunnybots2023.subsystems

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BeltSubsystemTest {

    @Test
    fun setUp() {
    }

    @Test
    fun setMotor() {

    }

    @Test
    fun stopMotor() {
    }

    @Test
    fun getSpeed() {
        val beltSubsystemGetSpeed = BeltSubsystem
        assertEquals(0.0,beltSubsystemGetSpeed.getSpeed())

    }
}