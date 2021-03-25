package ru.sbt.mipt.oop.Alarms;

import ru.sbt.mipt.oop.HouseContainment.Alarm;
import ru.sbt.mipt.oop.HouseServants.HouseGuard;
import ru.sbt.mipt.oop.HouseServants.TheRedQueen;
import ru.sbt.mipt.oop.SmartHome;

public class AlarmActivatedState extends AlarmState{
    private Alarm alarm;
    private SmartHome smartHome;
    public AlarmActivatedState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void getAlarmed() {

    }

    @Override
    public void invokeGuard() {
        HouseGuard guard = new TheRedQueen(smartHome);
        guard.performAlarmProtocol();
    }
}
