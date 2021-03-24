package ru.sbt.mipt.oop.HouseNavigators;

import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.SmartHome;

public class RoomFinder {
    private final SmartHome smartHome;

    public RoomFinder (SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public Room findRoomByDoor(Door door) {
        for (Room room : smartHome.getRooms()) {
            if (room.getDoors().contains(door)) {
                return room;
            }
        }
        return null;
    }

    public Room findRoomByLight (Light light) {
        for (Room room : smartHome.getRooms()) {
            if (room.getLights().contains(light)) {
                return room;
            }
        }
        return null;
    }
}
