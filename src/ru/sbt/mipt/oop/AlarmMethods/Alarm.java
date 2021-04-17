package ru.sbt.mipt.oop.AlarmMethods;

import ru.sbt.mipt.oop.AlarmMethods.AlarmState;
import ru.sbt.mipt.oop.AlarmMethods.DeactivatedAlarmState;

public class Alarm {
    private AlarmState state;
    private String code;

    public Alarm() {
        this.state = new DeactivatedAlarmState(this);
        this.code = "";
    }

    public boolean isOnPatrol() {
        return !(state instanceof DeactivatedAlarmState);
    }

    public boolean isOnAlertMode() {
        return state instanceof OnAlertModeAlarmState;
    }

    void setState(AlarmState state) {
        this.state = state;
    }

    String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void patrol(String code) {
        state.patrol(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void toAlertMode() {
        state.toAlertMode();
    }
}
