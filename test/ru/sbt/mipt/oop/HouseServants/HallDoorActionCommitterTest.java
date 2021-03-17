package ru.sbt.mipt.oop.HouseServants;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeReader;

import static org.junit.Assert.*;

public class HallDoorActionCommitterTest {

    @Test
    public void testCommitAction() throws Exception {
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "D0");
        SmartHome exp = SmartHomeReader.getSmartHome();
        SensorEvent lightEvent = new SensorEvent(SensorEventType.LIGHT_ON, "L4");
        SmartHome act = exp;
        new LightActionCommitter(act, lightEvent);
        new HallDoorActionCommitter(act);
        Assert.assertEquals(act, exp);
    }
}