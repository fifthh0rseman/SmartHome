package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorMethods.SensorEventType.*;

public class DoorEventHandler implements EventHandler {

    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        // событие от двери
        smartHome.execute(doorCandidate -> {
            if (!(doorCandidate instanceof Door)) {
                return;
            }

            Door door = (Door) doorCandidate;

            if (!door.getId().equals(event.getObjectId())) {
                return;
            }

            if (event.getType() == DOOR_CLOSED) {
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " was closed.");
            } else {
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " was opened.");
            }
        });
    }
}