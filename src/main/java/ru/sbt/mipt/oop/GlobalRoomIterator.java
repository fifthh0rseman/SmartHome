package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.HouseContainment.Room;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class GlobalRoomIterator implements GlobalObjectIterator {
    private Collection<Room> houseRooms;
    private int currentPosition;

    public GlobalRoomIterator(Collection<Room> rooms) {
        this.houseRooms = rooms;
    }

    @Override
    public Object getNext() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }
}
