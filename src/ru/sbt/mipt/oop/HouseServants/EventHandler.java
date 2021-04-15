package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public interface EventHandler {
    void handleEvent(SmartHome smartHome, SensorEvent sensorEvent);
}
