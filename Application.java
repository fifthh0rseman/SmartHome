package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.HouseServants.DoorActionCommitter;
import ru.sbt.mipt.oop.HouseServants.LightActionCommitter;
import ru.sbt.mipt.oop.SensorMethods.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static ru.sbt.mipt.oop.SensorMethods.SensorEventType.*;

public class Application {

    public static void main (String[] args) throws Exception {
        // считываем состояние дома из файла
        SmartHome smartHome = null;
        try {
            SmartHomeReader reader = new SmartHomeReaderJSON();
            smartHome = reader.getSmartHome();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // начинаем цикл обработки событий
        if (smartHome != null) {
            EventReceiver receiver = new TenEventReceiver(smartHome);
            receiver.receiveEvent();
        } else {
            throw new Exception("File is empty or damaged. Can not read.");
        }
    }
}
