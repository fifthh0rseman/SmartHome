package ru.sbt.mipt.oop.RemoteControllers.Commands;

import ru.sbt.mipt.oop.AlarmMethods.Alarm;
import ru.sbt.mipt.oop.SmartHome;

public class AlarmToAlertModeCommand implements Command {

    private final SmartHome smartHome;
    public AlarmToAlertModeCommand(SmartHome smartHome){
        this.smartHome = smartHome;
    }
    @Override
    public void execute() {
        Alarm alarm = smartHome.getAlarm();
        alarm.toAlertMode();
    }
}
