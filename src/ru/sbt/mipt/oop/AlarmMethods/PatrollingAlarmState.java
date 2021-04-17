package ru.sbt.mipt.oop.AlarmMethods;

public class PatrollingAlarmState implements AlarmState {
    private final Alarm alarm;

    public PatrollingAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void patrol(String code) {
        //already activated
        System.out.println("Alarm has been already activated. Alert mode turned on!");
        alarm.setState(new OnAlertModeAlarmState(alarm));
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setState(new DeactivatedAlarmState(alarm));
            System.out.println("Alarm successfully deactivated.");
        } else {
            toAlertMode();
            System.out.println("Wrong code. Alert mode turned on!");
        }
    }

    @Override
    public void toAlertMode() {
        alarm.setState(new OnAlertModeAlarmState(alarm));
    }
}
