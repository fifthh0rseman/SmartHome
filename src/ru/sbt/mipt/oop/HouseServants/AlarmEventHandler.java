package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;


public class AlarmEventHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {

        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            smartHome.getAlarm().patrol(event.getObjectId());
        }

        if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            smartHome.getAlarm().deactivate(event.getObjectId());
        }
    }
}
