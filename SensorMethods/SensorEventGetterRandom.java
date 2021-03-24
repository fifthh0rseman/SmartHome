package ru.sbt.mipt.oop.SensorMethods;

import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.SmartHome;

import java.util.ArrayList;
import java.util.Random;

import static ru.sbt.mipt.oop.SensorMethods.SensorEventType.*;

public class SensorEventGetterRandom implements SensorEventGetter {
    /*public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }*/

    public SensorEvent getNextSensorEvent(SmartHome smartHome) {
        ArrayList<Room> rooms = (ArrayList<Room>) smartHome.getRooms();
        ArrayList<Light> lights = new ArrayList<>();
        ArrayList<String> doorsIDs = new ArrayList<>();
        for (Room room : rooms) {
            lights.addAll(room.getLights());
            for (Door door : room.getDoors()) {
                if (!doorsIDs.contains(door.getId())) {
                    doorsIDs.add(door.getId());
                }
            }
        }
        int rndLight = new Random().nextInt(lights.size());
        int rndDoor = new Random().nextInt(doorsIDs.size());
        int rndEvent = new Random().nextInt(4);
        SensorEventType sensorEventType = SensorEventType.values()[rndEvent];
        if (sensorEventType == LIGHT_ON || sensorEventType == LIGHT_OFF) {
            return new SensorEvent(sensorEventType, "L" + rndLight);
        } else if (sensorEventType == DOOR_OPEN || sensorEventType == DOOR_CLOSED) {
            return new SensorEvent(sensorEventType, "D" + rndDoor);
        } else {
            return null;
        }
    }

    public SensorEvent getNextSensorEvent(SensorEventType commandType, String objectID) {
        return new SensorEvent(commandType, objectID);
    }
}
