package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.MessageSenders.AlarmMessageSender;
import ru.sbt.mipt.oop.MessageSenders.MessageSender;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import java.util.List;

public class TheHouseGuardDecorator implements EventHandler {

    private final List<EventHandler> eventHandlers;
    private final MessageSender sender;

    public TheHouseGuardDecorator(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
        this.sender = new AlarmMessageSender();
    }

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.ALARM_DEACTIVATE || event.getType() == SensorEventType.ALARM_ACTIVATE) {
            return;
        }

        if (smartHome.getAlarm().isOnAlertMode()) {
            sender.sendMessage("The intruder is still at the house!");
            return;
        }

        eventHandlers.forEach(handler -> handler.handleEvent(smartHome, event));

        if (smartHome.getAlarm().isOnPatrol()) {
            smartHome.getAlarm().toAlertMode();
            sender.sendMessage("Security breach! Alarm has been triggered!");
        }
    }
}
