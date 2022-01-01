package creep

import memory.sourceObjectIds
import screeps.api.*

class HarvestCreep(val creep: Creep) {
    fun harvestSource() {
        val sourceID = creep.room.memory.sourceObjectIds[0]
        val sourceObject = Game.getObjectById<Source>(sourceID)

        if (sourceObject != null) {
            val harvestValue = creep.harvest(sourceObject)
            if (harvestValue == ERR_NOT_IN_RANGE) {
                creep.moveTo(sourceObject)
            }
        }
    }
}