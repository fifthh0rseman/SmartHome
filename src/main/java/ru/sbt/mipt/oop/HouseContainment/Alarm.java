package ru.sbt.mipt.oop.HouseContainment;

import ru.sbt.mipt.oop.Alarms.AlarmDeactivatedState;
import ru.sbt.mipt.oop.Alarms.AlarmState;

public class Alarm {
    private AlarmState state;

    public Alarm () {
        this.state = new AlarmDeactivatedState(this);
    }

    public void changeAlarmState (AlarmState alarmState) {
        this.state = alarmState;
    }

    public AlarmState getState() {
        return state;
    }

    public void getAlarmed() {
        state.getAlarmed();
    }

    public void invokeGuard() {
        state.invokeGuard();
    }
}
