package ru.sbt.mipt.oop.HouseServants;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.HouseNavigators.DoorFinder;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeReader;

public class DoorActionCommitterTest {

    @Test
    public void testCommitDoorAction1() throws Exception {
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "D3");
        SmartHome act = SmartHomeReader.getSmartHome();
        SmartHome exp = act;
        DoorFinder finder = new DoorFinder(exp);
        Door door = finder.find(sensorEvent);
        door.setOpen(false);
        DoorActionCommitter committer = new DoorActionCommitter(act, sensorEvent);
        committer.commitAction();
        Assert.assertEquals(exp, act);
    }

    @Test
    public void testCommitDoorAction2() throws Exception {
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_OPEN, "D5");
        SmartHome act = SmartHomeReader.getSmartHome();
        SmartHome exp = act;
        DoorFinder finder = new DoorFinder(exp);
        Door door = finder.find(sensorEvent);
        door.setOpen(false);
        DoorActionCommitter committer = new DoorActionCommitter(act, sensorEvent);
        committer.commitAction();
        Assert.assertEquals(exp, act);
    }
}