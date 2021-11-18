package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.CommandSenders.CommandSender;
import ru.sbt.mipt.oop.CommandSenders.CommandType;
import ru.sbt.mipt.oop.CommandSenders.FakeCommandSender;
import ru.sbt.mipt.oop.CommandSenders.SensorCommand;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.MessageSenders.MessageSender;
import ru.sbt.mipt.oop.SensorMethods.*;
import ru.sbt.mipt.oop.SmartHome;

public class HallDoorEventHandler implements EventHandler {
    private CommandSender sender;

    public HallDoorEventHandler(CommandSender sender) {
        this.sender = sender;
    }

    public void handleEvent (SmartHome smartHome, SensorEvent sensorEvent) {

        if (sensorEvent.getType() == SensorEventType.DOOR_CLOSED && sensorEvent.getObjectId().equals("D0")) {
            smartHome.execute(lightCandidate -> {
                if (!(lightCandidate instanceof Light)) {
                    return;
                }
                Light light = (Light) lightCandidate;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sender.sendCommand(command);
            });
        }
    }
}
