package ru.sbt.mipt.oop.CommandSenders;

public class FakeCommandSender implements CommandSender {

    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
