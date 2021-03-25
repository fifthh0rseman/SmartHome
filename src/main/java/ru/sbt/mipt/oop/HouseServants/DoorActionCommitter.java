package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;

import static ru.sbt.mipt.oop.SensorMethods.SensorEventType.DOOR_OPEN;

public class DoorActionCommitter implements ActionCommitter {
    private final SmartHome smartHome;
    private final SensorEvent event;

    public DoorActionCommitter(SmartHome smartHome, SensorEvent event) {
        this.smartHome = smartHome;
        this.event = event;
    }

    public void commitAction() {
        // событие от двери
        smartHome.execute(new Action() {
            @Override
            public void act(Object doorCandidate) {
                if (!(doorCandidate instanceof Door)) {
                    return;
                }

                Door door = (Door) doorCandidate;

                if (!door.getId().equals(event.getObjectId())) {
                    return;
                }

                if (event.getType() == DOOR_OPEN) {
                    door.setOpen(true);
                    System.out.println("Door " + door.getId() + " was closed.");
                } else {
                    door.setOpen(false);
                    System.out.println("Door " + door.getId() + " was opened.");
                }
            }
        });
    }
}