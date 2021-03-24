package ru.sbt.mipt.oop.SensorMethods;

import ru.sbt.mipt.oop.HouseServants.DoorActionCommitter;
import ru.sbt.mipt.oop.HouseServants.LightActionCommitter;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorMethods.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorMethods.SensorEventType.DOOR_CLOSED;

public class TenEventReceiver implements EventReceiver {
    private final SmartHome smartHome;

    public TenEventReceiver (SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    public void receiveEvent () throws Exception {
        SensorEventGetter eventGetter = new SensorEventGetterRandom();
        SensorEvent event = eventGetter.getNextSensorEvent(smartHome);
        int ctr = 0;
        while (event != null) {
            System.out.println("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                // событие от источника света
                new LightActionCommitter(smartHome, event).commitAction();
            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                // событие от двери
                new DoorActionCommitter(smartHome, event).commitAction();
            }
            ctr++;
            if (ctr == 10) {
                break;
            }
            event = eventGetter.getNextSensorEvent(smartHome);
        }
    }
}
