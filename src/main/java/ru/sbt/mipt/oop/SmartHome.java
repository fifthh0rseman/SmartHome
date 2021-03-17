package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.HouseContainment.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements GlobalActionCommitter {
    private final Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void commitGlobalAction(Action action) {

    }
}
