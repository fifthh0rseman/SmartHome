package ru.sbt.mipt.oop.MessageSenders;

public class AlarmMessageSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
