package HouseServantsTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.HouseServants.HallDoorEventHandler;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Arrays;
import java.util.Collections;

public class HallDoorEventHandlerTest {
    private SmartHome smartHome;
    @Before
    public void createTestSmartHome() {
        Room corridor = new Room(Arrays.asList(new Light("L0", false), new Light("L1", false)),
                Arrays.asList(new Door(false, "D0"), new Door(false, "D1"), new Door(false, "D2"), new Door(true, "D3")),
                "Corridor");
        Room restroom = new Room(Arrays.asList(new Light("L8", false), new Light("L9", false)),
                Arrays.asList(new Door(false, "D1"), new Door(false, "D4")),
                "Restroom");
        Room restroomBalcony = new Room(Collections.singletonList(new Light("L10", false)),
                Collections.singletonList(new Door(false, "D4")),
                "Restroom Balcony");
        Room childrenRoom = new Room(Arrays.asList(new Light("L2", true), new Light("L3", false)),
                Collections.singletonList(new Door(false, "D2")),
                "Children Room");
        Room kitchen = new Room(Arrays.asList(new Light("L4", false), new Light("L5", true), new Light("L6", true)),
                Arrays.asList(new Door(true, "D3"), new Door(false, "D5")),
                "Kitchen");
        Room kitchenBalcony = new Room(Collections.singletonList(new Light("L7", false)),
                Collections.singletonList(new Door(false, "D5")),
                "Kitchen Balcony");
        this.smartHome = new SmartHome(Arrays.asList(
                corridor, restroom, restroomBalcony, kitchenBalcony, kitchen, childrenRoom));
    }

    @Test
    public void testHandleHallDoorEvent() {
        SensorEvent hallDoorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "D0");
        Room corridor = new Room(Arrays.asList(new Light("L0", false), new Light("L1", false)),
                Arrays.asList(new Door(false, "D0"), new Door(false, "D1"), new Door(false, "D2"), new Door(true, "D3")),
                "Corridor");
        Room restroom = new Room(Arrays.asList(new Light("L8", false), new Light("L9", false)),
                Arrays.asList(new Door(false, "D1"), new Door(false, "D4")),
                "Restroom");
        Room restroomBalcony = new Room(Collections.singletonList(new Light("L10", false)),
                Collections.singletonList(new Door(false, "D4")),
                "Restroom Balcony");
        Room childrenRoom = new Room(Arrays.asList(new Light("L2", false), new Light("L3", false)),
                Collections.singletonList(new Door(false, "D2")),
                "Children Room");
        Room kitchen = new Room(Arrays.asList(new Light("L4", false), new Light("L5", false), new Light("L6", false)),
                Arrays.asList(new Door(true, "D3"), new Door(false, "D5")),
                "Kitchen");
        Room kitchenBalcony = new Room(Collections.singletonList(new Light("L7", false)),
                Collections.singletonList(new Door(false, "D5")),
                "Kitchen Balcony");
        SmartHome expected = new SmartHome(Arrays.asList(
                corridor, restroom, restroomBalcony, kitchenBalcony, kitchen, childrenRoom));

        new HallDoorEventHandler().handleEvent(smartHome, hallDoorEvent);
        Assert.assertEquals(smartHome, expected);
    }

    @Test
    public void testNotHallDoorEvent () {
        String testDoorID = "D2";
        SensorEvent notHallDoorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, testDoorID);
        Room corridor = new Room(Arrays.asList(new Light("L0", false), new Light("L1", false)),
                Arrays.asList(new Door(false, "D0"), new Door(false, "D1"), new Door(false, "D2"), new Door(true, "D3")),
                "Corridor");
        Room restroom = new Room(Arrays.asList(new Light("L8", false), new Light("L9", false)),
                Arrays.asList(new Door(false, "D1"), new Door(false, "D4")),
                "Restroom");
        Room restroomBalcony = new Room(Collections.singletonList(new Light("L10", false)),
                Collections.singletonList(new Door(false, "D4")),
                "Restroom Balcony");
        Room childrenRoom = new Room(Arrays.asList(new Light("L2", true), new Light("L3", false)),
                Collections.singletonList(new Door(false, "D2")),
                "Children Room");
        Room kitchen = new Room(Arrays.asList(new Light("L4", false), new Light("L5", true), new Light("L6", true)),
                Arrays.asList(new Door(true, "D3"), new Door(false, "D5")),
                "Kitchen");
        Room kitchenBalcony = new Room(Collections.singletonList(new Light("L7", false)),
                Collections.singletonList(new Door(false, "D5")),
                "Kitchen Balcony");
        SmartHome expected = new SmartHome(Arrays.asList(
                corridor, restroom, restroomBalcony, kitchenBalcony, kitchen, childrenRoom));
        new HallDoorEventHandler().handleEvent(smartHome, notHallDoorEvent);
        Assert.assertEquals(smartHome, expected);
    }
}