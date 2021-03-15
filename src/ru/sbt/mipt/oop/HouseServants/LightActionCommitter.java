package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.HouseNavigators.LightFinder;
import ru.sbt.mipt.oop.HouseNavigators.RoomFinder;
import ru.sbt.mipt.oop.SensorMethods.CommandSender;
import ru.sbt.mipt.oop.SensorMethods.CommandType;
import ru.sbt.mipt.oop.SensorMethods.SensorCommand;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;

import static ru.sbt.mipt.oop.SensorMethods.SensorEventType.LIGHT_ON;

public class LightActionCommitter implements ActionCommitter {
    private final SmartHome smartHome;
    private final SensorEvent event;

    public LightActionCommitter(SmartHome smartHome, SensorEvent event) {
        this.smartHome = smartHome;
        this.event = event;
    }

    public void commitLightAction() throws Exception {
        Light light = new LightFinder(smartHome).findLightByEvent(event);
        Room room = new RoomFinder(smartHome).findRoomByLight(light);
        if (light != null) {
            if (room != null) {
                if (event.getType() == LIGHT_ON) {
                    light.setOn(true);
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                } else {
                    light.setOn(false);
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                }
            } else {
                throw new Exception("Database error! Light source" + light.getId() + "is not found in any room.");
            }
        } else {
            throw new Exception("Database error! Cannot find the object" + event.getObjectId() + "in your Smart Home.");
        }
    }

    public void setAllLightsOff (SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(false);
                CommandSender sender = new CommandSender(new SensorCommand(CommandType.LIGHT_OFF, light.getId()));
                sender.sendCommand();
            }
        }
    }
}
