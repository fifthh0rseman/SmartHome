package ru.sbt.mipt.oop.Alarms.Events;

public class AlarmEvent {
    private final AlarmEventType alarmEventType;
    private final String code;

    public AlarmEvent(AlarmEventType alarmEventType, String code) {
        this.alarmEventType = alarmEventType;
        this.code = code;
    }

    public AlarmEventType getAlarmEventType() {
        return alarmEventType;
    }

    public String getCode() {
        return code;
    }
}
