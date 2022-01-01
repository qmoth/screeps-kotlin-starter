package creep

import memory.sourceObjectIds
import screeps.api.*

import screeps.api.Game
import screeps.api.Source

class CarryCreep(val creep: Creep) {
    fun carryEnergy() {
        //val sourceID = creep.room.memory.sourceObjectIds[0]
        //val sourceObject = Game.getObjectById<Source>(sourceID)

        //if (sourceObject != null) {
            //val harvestValue = creep.harvest(sourceObject)
            //if (harvestValue == ERR_NOT_IN_RANGE) {
                //creep.moveTo(sourceObject)