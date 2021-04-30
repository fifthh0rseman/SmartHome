package ru.sbt.mipt.oop.SensorMethods;

import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.SmartHome;

import java.util.ArrayList;
import java.util.Random;

import static ru.sbt.mipt.oop.SensorMethods.SensorEventType.*;

public class SensorEventGetterRandom implements SensorEventGetter {

    public SensorEvent getNextSensorEvent(SmartHome smartHome) {
        ArrayList<Light> lights = new ArrayList<>();
        ArrayList<String> doorsIDs = new ArrayList<>();
        smartHome.execute(doorCandidate -> {
            if (!(doorCandidate instanceof Door)) {
                return;
            }

            Door door = (Door) doorCandidate;

            if (!doorsIDs.contains(door.getId())) {
                doorsIDs.add(door.getId());
            }
        });

        smartHome.execute(lightCandidate -> {
            if (!(lightCandidate instanceof Light)) {
                return;
            }

            Light light = (Light) lightCandidate;

            lights.add(light);
        });

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
