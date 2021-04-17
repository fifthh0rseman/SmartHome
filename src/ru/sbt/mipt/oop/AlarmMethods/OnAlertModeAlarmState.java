package ru.sbt.mipt.oop.AlarmMethods;

public class OnAlertModeAlarmState implements AlarmState {
    private final Alarm alarm;

    public OnAlertModeAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void patrol(String code) {
        //already activated
        System.out.println("Alarm has been already activated.");
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setState(new DeactivatedAlarmState(alarm));
            System.out.println("Alarm successfully deactivated.");
        } else {
            System.out.println("Wrong code. Alert mode is still running!");
        }
    }

    @Override
    public void toAlertMode() {
        //already on alert mode
    }
}
