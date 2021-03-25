package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.SmartHome;

public class TheRedQueen implements HouseGuard {
    private final SmartHome smartHome;

    public TheRedQueen(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void performAlarmProtocol() {
        smartHome.getRooms().stream().flatMap(room -> room.getLights().stream()).forEach(light -> light.setOn(false));
        smartHome.getRooms().stream().flatMap(room -> room.getDoors().stream()).forEach(door -> door.setOpen(false));
        MessageSender sender = new AlarmMessageSender();
        sender.sendMessage();
    }
}
