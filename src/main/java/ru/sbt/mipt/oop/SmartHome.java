package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.HouseContainment.Alarm;
import ru.sbt.mipt.oop.HouseContainment.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

public class SmartHome implements Actionable {
    private final Collection<Room> rooms;
    private final Alarm alarm;

    public SmartHome(Alarm alarm) {
        this.alarm = alarm;
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms, Alarm alarm) {
        this.rooms = rooms;
        this.alarm = alarm;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Alarm getAlarm() {
        return alarm;
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
