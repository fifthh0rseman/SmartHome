package ru.sbt.mipt.oop.AlarmMethods;

public class DeactivatedAlarmState implements AlarmState{
    private final Alarm alarm;

    public DeactivatedAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void patrol(String code) {
        alarm.setCode(code);
        alarm.setState(new PatrollingAlarmState(alarm));
        System.out.println("Alarm has been set on patrol.");
    }

    @Override
    public void deactivate(String code) {
        System.out.println("Alarm is already deactivated.");
    }

    @Override
    public void toAlertMode() {

    }
}
