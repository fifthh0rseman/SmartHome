package ru.sbt.mipt.oop.Alarms;

import ru.sbt.mipt.oop.HouseContainment.Alarm;

public class AlarmPatrollingState extends AlarmState {
    private Alarm alarm;

    public AlarmPatrollingState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void getAlarmed() {
        alarm.changeAlarmState(new AlarmActivatedState(this.alarm));
        System.out.println("Alarm invoked!");
    }

    @Override
    public void invokeGuard() {

    }
}
