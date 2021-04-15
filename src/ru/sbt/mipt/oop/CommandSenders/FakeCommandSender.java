package ru.sbt.mipt.oop.CommandSenders;

public class FakeCommandSender implements CommandSender {
    private final SensorCommand command;

    public FakeCommandSender (SensorCommand command) {
        this.command = command;
    }

    public void sendCommand() {
        System.out.println("Pretend we're sending command " + this.command);
    }
}
