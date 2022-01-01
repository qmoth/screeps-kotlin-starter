import brain.BigBrain
import memory.reachedFullCapacity
import screeps.api.*
import screeps.api.structures.StructureController

/**
 * Entry point
 * is called by screeps
 *
 * must not be removed by DCE
 */
@Suppress("unused")
//This function loops the process of assigning roomObject the value of the next room on the list in the Game.room map
fun loop() {

    val bigBrain = BigBrain()

    bigBrain.findSources()

    for (room in Game.rooms.keys) {
        val roomObject = Game.rooms[room]!!
        //If there is a controller in the room, and I own the room- then...
        if (roomObject.controller != null && roomObject.controller!!.my) {
            //creates a temp variable named mySpawn and takes value from Game.spawns.keys
            for(mySpawn in Game.spawns.keys) {
                //using a spawn contained in mySpawn, spawn a creep with work, carry, move, move and name it dad + in game tick timer number
                Game.spawns[mySpawn]!!.spawnCreep(arrayOf(WORK, CARRY, MOVE, MOVE), "dad${Game.time}")
            }

            for(creepName in Game.creeps.keys) {
                val creep = Game.creeps[creepName]!!

                if (creep.store.getFreeCapacity() == 50) {
                   creep.memory.reachedFullCapacity = false
                }

                if (!creep.memory.reachedFullCapacity) {

                     val arrayOfSources = creep.room.find(FIND_SOURCES_ACTIVE)
                     if (arrayOfSources.isNotEmpty())  {
                         val oneSource = arrayOfSources[0]
                         val harvestValue = creep.harvest(oneSource)

                         if (harvestValue == ERR_NOT_IN_RANGE) {
                             creep.moveTo(oneSource)
                         }

                     }
                }
                if (creep.store.getFreeCapacity() == 0) {
                    creep.memory.reachedFullCapacity = true
                }
                if (creep.memory.reachedFullCapacity) {
                    val roomsController = creep.room.controller!!
                    val upgradeValue = creep.upgradeController(roomsController)
                    if (upgradeValue == ERR_NOT_IN_RANGE) {
                        creep.moveTo(roomsController)
                    }
                }
            }

        }
    }
}





