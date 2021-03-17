package ru.sbt.mipt.oop.HouseServants;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.HouseNavigators.LightFinder;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeReader;

public class LightActionCommitterTest {
    @Test
    public void commitAction1() throws Exception {
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.LIGHT_ON, "L3");
        SmartHome act = SmartHomeReader.getSmartHome();
        SmartHome exp = act;
        LightFinder finder = new LightFinder(exp);
        Light light = finder.find(sensorEvent);
        light.setOn(true);
        new LightActionCommitter(act, sensorEvent).commitAction();
        Assert.assertEquals(act, exp);
    }
    @Test
    public void commitAction2() throws Exception {
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.LIGHT_ON, "L5");
        SmartHome act = SmartHomeReader.getSmartHome();
        SmartHome exp = act;
        LightFinder finder = new LightFinder(exp);
        Light light = finder.find(sensorEvent);
        light.setOn(true);
        new LightActionCommitter(act, sensorEvent).commitAction();
        Assert.assertEquals(act, exp);
    }
}