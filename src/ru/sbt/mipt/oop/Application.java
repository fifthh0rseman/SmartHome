package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.HouseServants.DoorActionCommitter;
import ru.sbt.mipt.oop.HouseServants.LightActionCommitter;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventGetter;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorMethods.SensorEventType.*;

public class Application {

    public static void main (String[] args) throws Exception {
        // считываем состояние дома из файла
        SmartHome smartHome = null;
        try {
            smartHome = new SmartHomeReader().getSmartHome();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // начинаем цикл обработки событий
        if (smartHome != null) {
            SensorEventGetter eventGetter = new SensorEventGetter();
            SensorEvent event = eventGetter.getNextSensorEvent();
            while (event != null) {
                System.out.println("Got event: " + event);
                if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                    // событие от источника света
                    new LightActionCommitter(smartHome, event).commitLightAction();
                }
                if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                    // событие от двери
                    new DoorActionCommitter(smartHome, event).commitDoorAction();
                }

                event = eventGetter.getNextSensorEvent();
            }
        } else {
            throw new Exception("File is empty or damaged. Can not read.");
        }
    }
}
