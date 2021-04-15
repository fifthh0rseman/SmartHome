package ru.sbt.mipt.oop.EventRunners;

import ru.sbt.mipt.oop.HouseServants.EventHandler;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventGetter;
import ru.sbt.mipt.oop.SmartHome;

import java.util.List;

public class TenEventCyclicRunner implements EventRunner {
    private final SensorEventGetter getter;
    private final List<EventHandler> eventHandlers;

    public TenEventCyclicRunner(SensorEventGetter getter, List<EventHandler> eventHandlers) {
        this.getter = getter;
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void run(SmartHome smartHome) {
        // в этом примере берем 10 раз случайное событие
        int ctr = 0;
        SensorEvent event = getter.getNextSensorEvent(smartHome);
        while (event != null) {
            System.out.println("Got event:" + event);
            for (EventHandler handler : eventHandlers) {
                handler.handleEvent(smartHome, event);
            }
            event = getter.getNextSensorEvent(smartHome);
            ctr++;
            if (ctr == 10) {
                System.out.println("Event Cycle finished.");
                return;
            }
        }
    }
}
