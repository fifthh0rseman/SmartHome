package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.SensorMethods.CommandSender;
import ru.sbt.mipt.oop.SensorMethods.CommandType;
import ru.sbt.mipt.oop.SensorMethods.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;

public class HallDoorActionCommitter implements ActionCommitter {
    private final SmartHome smartHome;

    public HallDoorActionCommitter(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void commitAction () {
        smartHome.getRooms().stream().flatMap(room -> room.getLights().stream()).forEach(light -> {
            light.setOn(false);
            CommandSender sender = new CommandSender(new SensorCommand(CommandType.LIGHT_OFF, light.getId()));
            sender.sendCommand();
        });
    }
}
