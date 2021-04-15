package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.CommandSenders.CommandSender;
import ru.sbt.mipt.oop.CommandSenders.CommandType;
import ru.sbt.mipt.oop.CommandSenders.FakeCommandSender;
import ru.sbt.mipt.oop.CommandSenders.SensorCommand;
import ru.sbt.mipt.oop.SensorMethods.*;
import ru.sbt.mipt.oop.SmartHome;

public class HallDoorEventHandler implements EventHandler {

    public void handleEvent (SmartHome smartHome, SensorEvent sensorEvent) {

        if (sensorEvent.getType() == SensorEventType.DOOR_CLOSED && sensorEvent.getObjectId().equals("D0")) {
            smartHome.getRooms().stream().flatMap(room -> room.getLights().stream()).forEach(light -> {
                light.setOn(false);
                CommandSender sender = new FakeCommandSender(new SensorCommand(CommandType.LIGHT_OFF, light.getId()));
                sender.sendCommand();
            });
        }
    }
}
