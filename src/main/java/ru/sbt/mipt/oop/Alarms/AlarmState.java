package ru.sbt.mipt.oop.Alarms;

import ru.sbt.mipt.oop.HouseContainment.Alarm;

public abstract class AlarmState {
    private Alarm alarm;

    public AlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    public abstract void getAlarmed();
    public abstract void invokeGuard();
}
