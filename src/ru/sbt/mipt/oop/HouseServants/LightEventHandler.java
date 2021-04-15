package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;

import static ru.sbt.mipt.oop.SensorMethods.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {

    public void handleEvent(SmartHome smartHome, SensorEvent event) {

        smartHome.execute(new Action() {
            @Override
            public void act(Object lightCandidate) {
                if (!(lightCandidate instanceof Light)) {
                    return;
                }

                Light light = (Light) lightCandidate;

                if (!light.getId().equals(event.getObjectId())) {
                    return;
                }

                if (event.getType() == LIGHT_ON) {
                    light.setOn(true);
                    System.out.println("Light " + light.getId() + " was turned on.");
                } else {
                    light.setOn(false);
                    System.out.println("Light " + light.getId() + " was turned off.");
                }

            }
        });
    }
}
