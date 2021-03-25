package ru.sbt.mipt.oop.HouseServants;

public class AlarmMessageSender implements MessageSender {
    @Override
    public void sendMessage() {
        System.out.println("SECURITY BREACH! ALARM PROTOCOL PERFORMED!");
    }
}
