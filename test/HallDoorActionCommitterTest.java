package ru.sbt.mipt.oop.HouseServants;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Arrays;
import java.util.Collections;

public class HallDoorActionCommitterTest {

    @Test
    public void testSetAllLightsOff() {
        Room corridor = new Room(Arrays.asList(new Light("L0", false), new Light("L1", false)),
                Arrays.asList(new Door(false, "D0"), new Door(false, "D1"), new Door(false, "D2"), new Door(false, "D3")),
                "Corridor");
        Room restroom = new Room(Arrays.asList(new Light("L8", false), new Light("L9", false)),
                Arrays.asList(new Door(false, "D1"), new Door(false, "D5")),
                "Restroom");
        Room restroomBalcony = new Room(Collections.singletonList(new Light("L10", false)),
                Collections.singletonList(new Door(false, "D4")),
                "Restroom Balcony");
        Room childrenRoom = new Room(Arrays.asList(new Light("L2", true), new Light("L3", false)),
                Collections.singletonList(new Door(false, "D2")),
                "Children Room");
        Room kitchen = new Room(Arrays.asList(new Light("L4", false), new Light("L5", true), new Light("L6", true)),
                Arrays.asList(new Door(false, "D3"), new Door(false, "D5")),
                "Kitchen");
        Room kitchenBalcony = new Room(Collections.singletonList(new Light("L7", false)),
                Collections.singletonList(new Door(false, "D5")),
                "Kitchen Balcony");
        SmartHome act = new SmartHome(Arrays.asList(kitchen, childrenRoom, restroom, corridor, kitchenBalcony, restroomBalcony));
        SmartHome exp = new SmartHome(Arrays.asList(kitchen, childrenRoom, restroom, corridor, kitchenBalcony, restroomBalcony));
        for (Room room : exp.getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(false);
            }
        }
        new HallDoorActionCommitter(act).commitAction();
        Assert.assertEquals(act, exp);
    }
}