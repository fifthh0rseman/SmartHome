package ru.sbt.mipt.oop.MessageSenders;

public class AlertMessageSender implements MessageSender {
    @Override
    public void sendMessage() {
        System.out.println("Sensor signal detected during the alert mode! The intruder is still in the house!");
    }
}
