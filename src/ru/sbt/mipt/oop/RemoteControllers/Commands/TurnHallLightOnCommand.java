package ru.sbt.mipt.oop.RemoteControllers.Commands;

import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.SmartHome;

public class TurnHallLightOnCommand implements Command {
    private final SmartHome smartHome;

    public TurnHallLightOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                Room room = (Room) roomCandidate;
                if (room.getName().equals("Corridor")) {
                    room.execute(lightCandidate -> {
                        if (lightCandidate instanceof Light) {
                            ((Light) lightCandidate).setOn(true);
                        }
                    });
                }
            }
        });
    }
}
