package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.MessageSenders.AlertMessageSender;
import ru.sbt.mipt.oop.MessageSenders.PatrolMessageSender;
import ru.sbt.mipt.oop.SensorMethods.SensorEvent;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import java.util.List;

public class TheHouseGuardDecorator implements EventHandler {

    private final List<EventHandler> eventHandlers;

    public TheHouseGuardDecorator(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.ALARM_DEACTIVATE || event.getType() == SensorEventType.ALARM_ACTIVATE) {
            return;
        }

        if (smartHome.getAlarm().isOnAlertMode()) {
            new AlertMessageSender().sendMessage();
            return;
        }

        eventHandlers.forEach(handler -> handler.handleEvent(smartHome, event));

        if (smartHome.getAlarm().isOnPatrol()) {
            smartHome.getAlarm().toAlertMode();
            new PatrolMessageSender().sendMessage();
        }
    }
}
