package ru.sbt.mipt.oop.Alarms;

import ru.sbt.mipt.oop.HouseContainment.Alarm;

public class AlarmDeactivatedState extends AlarmState {
    public AlarmDeactivatedState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void getAlarmed() {

    }

    @Override
    public void invokeGuard() {

    }
}
