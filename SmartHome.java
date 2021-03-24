package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.HouseContainment.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

public class SmartHome implements Actionable {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartHome smartHome = (SmartHome) o;
        return Objects.equals(rooms, smartHome.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rooms);
    }

    @Override
    public void execute(Action action) {
        action.act(this);

        rooms.forEach(new Consumer<Room>() {
            @Override
            public void accept(Room room) {
                room.execute(action);
            }
        });
    }
}
