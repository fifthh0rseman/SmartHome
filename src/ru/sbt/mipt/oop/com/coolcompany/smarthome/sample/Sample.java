package ru.sbt.mipt.oop.com.coolcompany.smarthome.sample;

import ru.sbt.mipt.oop.com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.com.coolcompany.smarthome.events.SensorEventsManager;

public class Sample {

    public static void main(String[] args) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(event -> System.out.println("Event type [" + event.getEventType() + "] from object with id=" + event.getObjectId() + "]"));
        sensorEventsManager.start();
    }
}
