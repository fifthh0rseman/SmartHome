package ru.sbt.mipt.oop.MessageSenders;

public class PatrolMessageSender implements MessageSender{
    @Override
    public void sendMessage() {
        System.out.println("Security breach detected! Alarm has been set on alert mode!");
    }
}
