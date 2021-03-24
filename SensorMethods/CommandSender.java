package ru.sbt.mipt.oop.SensorMethods;

import ru.sbt.mipt.oop.SensorMethods.SensorCommand;

public class CommandSender {
    private final SensorCommand command;

    public CommandSender (SensorCommand command) {
        this.command = command;
    }

    public void sendCommand() {
        System.out.println("Pretend we're sending command " + this.command);
    }
}
