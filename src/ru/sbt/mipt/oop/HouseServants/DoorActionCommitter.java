package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.HouseNavigators.DoorFinder;
import ru.sbt.mipt.oop.HouseNavigators.RoomFinder;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;

import static ru.sbt.mipt.oop.SensorMethods.SensorEventType.DOOR_OPEN;

public class DoorActionCommitter implements ActionCommitter {
    private final SmartHome smartHome;
    private final SensorEvent event;

    public DoorActionCommitter(SmartHome smartHome, SensorEvent event) {
        this.smartHome = smartHome;
        this.event = event;
    }

    public void commitDoorAction() throws Exception {
        Door door = new DoorFinder(smartHome).findDoorByEvent(event);
        Room room = new RoomFinder(smartHome).findRoomByDoor(door);
        if (door != null) {
            if (room != null) {
                if (event.getType() == DOOR_OPEN) {
                    door.setOpen(true);
                    System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                } else {
                    door.setOpen(false);
                    System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    if (room.getName().equals("hall")) {
                        new LightActionCommitter(smartHome, event).setAllLightsOff(smartHome);
                    }
                }
            } else {
                throw new Exception("Database error! Door" + door.getId() + "is not found in any room.");
            }
        } else {
            throw new Exception("Database error! Cannot find the object" + event.getObjectId() + "in your Smart Home.");
        }
    }
}