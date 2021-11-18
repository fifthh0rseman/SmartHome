package ru.sbt.mipt.oop.Adapters;

import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.ExternalSources.com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.ExternalSources.com.coolcompany.smarthome.events.EventHandler;

import java.util.Map;

public class CCEventHandlerAdapter implements EventHandler {
    private final ru.sbt.mipt.oop.HouseServants.EventHandler eventHandler;
    private final SmartHome smartHome;
    private final Map<String, SensorEventType> ccEventToSensorEvent;

    public CCEventHandlerAdapter(ru.sbt.mipt.oop.HouseServants.EventHandler handler, SmartHome smartHome, Map<String, SensorEventType> ccEventToSensorEvent) {
        this.eventHandler = handler;
        this.smartHome = smartHome;
        this.ccEventToSensorEvent = ccEventToSensorEvent;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventHandler.handleEvent(smartHome,
                new SensorEvent(
                        ccEventToSensorEvent.getOrDefault(event.getEventType(), SensorEventType.UNKNOWN_EVENT),
                        event.getObjectId()
                )
        );
    }
}
