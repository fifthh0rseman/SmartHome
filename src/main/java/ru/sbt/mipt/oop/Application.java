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
            smartHome = SmartHomeReader.getSmartHome();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // начинаем цикл обработки событий
        if (smartHome != null) {
            SensorEventGetter eventGetter = new SensorEventGetter();
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
        } else {
            throw new Exception("File is empty or damaged. Can not read.");
        }
    }
}
