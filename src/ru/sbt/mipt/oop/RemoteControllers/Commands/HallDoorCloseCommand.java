package ru.sbt.mipt.oop.RemoteControllers.Commands;

import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.SmartHome;

public class HallDoorCloseCommand implements Command {
    private final SmartHome smartHome;

    public HallDoorCloseCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("Corridor")) {
                    room.execute(hallDoorCandidate -> {
                        if (hallDoorCandidate instanceof Door) {
                           Door maybeHallDoor = (Door) hallDoorCandidate;
                           if (maybeHallDoor.getId().equals("D0")) {
                               maybeHallDoor.setOpen(false);
                           }
                        }
                    });
                }
            }
        });
    }
}
