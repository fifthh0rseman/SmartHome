package ru.sbt.mipt.oop.HouseServants;

import ru.sbt.mipt.oop.Alarms.AlarmActivatedState;
import ru.sbt.mipt.oop.Alarms.AlarmDeactivatedState;
import ru.sbt.mipt.oop.Alarms.AlarmPatrollingState;
import ru.sbt.mipt.oop.Alarms.Events.AlarmEvent;
import ru.sbt.mipt.oop.Alarms.Events.AlarmEventType;
import ru.sbt.mipt.oop.SmartHome;

public class AlarmActionCommitter implements ActionCommitter {
    private AlarmEvent event;
    private SmartHome smartHome;

    public AlarmActionCommitter(AlarmEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    @Override
    public void commitAction() {
        if (event.getAlarmEventType() == AlarmEventType.ALARM_ACTIVATE && event.getCode().equals("1000")) {
            smartHome.getAlarm().changeAlarmState(new AlarmPatrollingState(smartHome.getAlarm()));
            System.out.println("Permission granted. Alarm has been set on.");
        } else if (event.getAlarmEventType() == AlarmEventType.ALARM_DEACTIVATE && event.getCode().equals("1111")){
            smartHome.getAlarm().changeAlarmState(new AlarmDeactivatedState(smartHome.getAlarm()));
            System.out.println("Permissiom granted. Alarm has been set off.");
        }
    }
}
