package ru.sbt.mipt.oop.RemoteControllers.Commands;

import ru.sbt.mipt.oop.AlarmMethods.Alarm;
import ru.sbt.mipt.oop.SmartHome;

public class AlarmActivateCommand implements Command {
    private final SmartHome smartHome;
    private final String code;


    public AlarmActivateCommand(SmartHome smartHome, String code) {
        this.smartHome = smartHome;
        this.code = code;
    }


    @Override
    public void execute() {
        Alarm alarm = smartHome.getAlarm();
        alarm.patrol(code);
    }
}
