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
fun loop() {
    for (room in Game.rooms.keys) {
        val roomObject = Game.rooms[room]!!
        if (roomObject.controller != null && roomObject.controller!!.my) {

            for(mySpawn in Game.spawns.keys) {
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





