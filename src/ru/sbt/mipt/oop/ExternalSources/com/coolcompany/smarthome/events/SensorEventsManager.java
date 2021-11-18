package ru.sbt.mipt.oop.ExternalSources.com.coolcompany.smarthome.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class SensorEventsManager {
    private final String[] eventTypes = new String[] { "LightIsOn", "LightIsOff", "DoorIsOpen", "DoorIsClosed", "DoorIsLocked", "DoorIsUnlocked" };

    private final Collection<EventHandler> handlers = new ArrayList<>();

    public void registerEventHandler(EventHandler handler) {
        this.handlers.add(handler);
    }

    public void start() {
        CCSensorEvent event = getNextSensorEvent();
        int ctr = 0;
        while (ctr < 10) {
            for (EventHandler handler : handlers) {
                handler.handleEvent(event);
            }
            event = getNextSensorEvent();
            ctr++;
        }
    }

    private CCSensorEvent getNextSensorEvent() {
        String[] objectClasses = {"L", "D"};
        int rndObjectClass = new Random().nextInt(objectClasses.length);
        int rndObjectId = new Random().nextInt(10);
        int rndEvent = new Random().nextInt(eventTypes.length);
        String objectId = objectClasses[rndObjectClass] + rndObjectId;
        String sensorEventType = eventTypes[rndEvent];
        return new CCSensorEvent(sensorEventType, objectId);
    }


}
