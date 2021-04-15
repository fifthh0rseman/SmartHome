package HouseServantsTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.HouseContainment.Room;
import ru.sbt.mipt.oop.HouseServants.DoorEventHandler;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class DoorEventHandlerTest {

    private SmartHome smartHome;
    @Before
    public void createTestSmartHome() {
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.LIGHT_ON, "L3");
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
    public void testDoorClosed() throws Exception {
        String testDoorID = "D3";

        SensorEvent closeTheDoor3 = new SensorEvent(SensorEventType.DOOR_CLOSED, testDoorID);
        Room corridor = new Room(Arrays.asList(new Light("L0", false), new Light("L1", false)),
                Arrays.asList(new Door(false, "D0"), new Door(false, "D1"), new Door(false, "D2"), new Door(false, "D3")),
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
                Arrays.asList(new Door(false, "D3"), new Door(false, "D5")),
                "Kitchen");
        Room kitchenBalcony = new Room(Collections.singletonList(new Light("L7", false)),
                Collections.singletonList(new Door(false, "D5")),
                "Kitchen Balcony");
        SmartHome exp = new SmartHome(Arrays.asList(
                corridor, restroom, restroomBalcony, kitchenBalcony, kitchen, childrenRoom));
        new DoorEventHandler().handleEvent(smartHome, closeTheDoor3);

        Assert.assertEquals(smartHome, exp);
    }

    @Test
    public void testDoorOpen() {
        String testDoorID = "D5";

        SensorEvent openTheDoor5 = new SensorEvent(SensorEventType.DOOR_OPEN, testDoorID);
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
                Arrays.asList(new Door(true, "D3"), new Door(true, "D5")),
                "Kitchen");
        Room kitchenBalcony = new Room(Collections.singletonList(new Light("L7", false)),
                Collections.singletonList(new Door(true, "D5")),
                "Kitchen Balcony");
        SmartHome exp = new SmartHome(Arrays.asList(
                corridor, restroom, restroomBalcony, kitchenBalcony, kitchen, childrenRoom));
        new DoorEventHandler().handleEvent(smartHome, openTheDoor5);

        Assert.assertEquals(smartHome, exp);
    }

    @Test
    public void testNullDoorOpen() {
        String testDoorID = "DAMN_DOOR";
        SensorEvent openTheDamnDoor = new SensorEvent(SensorEventType.DOOR_OPEN, testDoorID);

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
        SmartHome act = new SmartHome(Arrays.asList(
                corridor, restroom, restroomBalcony, kitchenBalcony, kitchen, childrenRoom));

        new DoorEventHandler().handleEvent(act, openTheDamnDoor);

        /*for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                System.out.println(door.getId() + " : " + door.isOpen());
            }
        }

        System.out.println("\n");

        for (Room room : act.getRooms()) {
            for (Door door : room.getDoors()) {
                System.out.println(door.getId() + " : " + door.isOpen());
            }
        }*/

        Assert.assertEquals(smartHome, act);
    }
}