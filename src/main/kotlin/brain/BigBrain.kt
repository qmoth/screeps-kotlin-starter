package brain

import memory.sourceObjectIds
import screeps.api.*

class BigBrain {
    private val roomsIOwn = mutableListOf<Room>()

    init {
        for (roomObject in Game.rooms.values) {

            if (roomObject.controller != null && roomObject.controller!!.my ) {
                roomsIOwn.add(roomObject)
            }
        }
    }

    /**
     * We want this function to store the value of a source object that is found in a room we own
     */
    fun findSources() {
        for(currentRoom in roomsIOwn) {
            val returnedSources = currentRoom.find(FIND_SOURCES)
            for(sourceObjects in returnedSources) {
                if(!currentRoom.memory.sourceObjectIds.contains(sourceObjects.id)) {
                    currentRoom.memory.sourceObjectIds = currentRoom.memory.sourceObjectIds.plus(sourceObjects.id)
                }
            }
        }
    }
}
