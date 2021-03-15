package ru.sbt.mipt.oop.HouseNavigators;

import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public class LightFinder {
    private final SmartHome smartHome;

    public LightFinder (SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    public Light findLightByEvent(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    return light;
                }
            }
        }
        return null;
    }
}
