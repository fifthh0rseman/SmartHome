package ru.sbt.mipt.oop.HouseNavigators;

import ru.sbt.mipt.oop.SensorMethods.SensorEvent;

public interface HomeNavigator {
    public Object find(SensorEvent sensorEvent);
}
