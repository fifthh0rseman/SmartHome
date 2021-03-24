package ru.sbt.mipt.oop.HouseNavigators;

import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public class DoorFinder implements HomeNavigator {
    private final SmartHome smartHome;

    public DoorFinder (SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public Door find(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    return door;
                }
            }
        }
        return null;
    }
}
