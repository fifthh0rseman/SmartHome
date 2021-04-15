package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.EventRunners.TenEventCyclicRunner;
import ru.sbt.mipt.oop.EventRunners.EventRunner;
import ru.sbt.mipt.oop.HouseServants.DoorEventHandler;
import ru.sbt.mipt.oop.HouseServants.HallDoorEventHandler;
import ru.sbt.mipt.oop.HouseServants.LightEventHandler;
import ru.sbt.mipt.oop.Readers.SmartHomeReader;
import ru.sbt.mipt.oop.Readers.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.SensorMethods.SensorEventGetterRandom;

import java.io.IOException;
import java.util.Arrays;

public class Application {

    private SmartHome smartHome;
    private EventRunner eventRunner;

    public Application(SmartHome smartHome, EventRunner eventRunner) {
        this.smartHome = smartHome;
        this.eventRunner = eventRunner;
    }

    public static void main (String[] args) throws Exception {
        // считываем состояние дома из файла
        SmartHome smartHome;
        try {
            SmartHomeReader reader = new SmartHomeReaderJSON();
            smartHome = reader.getSmartHome();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        new Application(smartHome, new TenEventCyclicRunner(new SensorEventGetterRandom(),
                Arrays.asList(new DoorEventHandler(), new LightEventHandler(), new HallDoorEventHandler()))).launch();
    }

    public void launch() {
        // начинаем цикл обработки событий
        this.eventRunner.run(smartHome);
    }
}