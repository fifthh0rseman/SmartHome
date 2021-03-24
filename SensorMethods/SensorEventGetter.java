package ru.sbt.mipt.oop.SensorMethods;

import ru.sbt.mipt.oop.SmartHome;

public interface SensorEventGetter {
    SensorEvent getNextSensorEvent(SmartHome smartHome);
}
