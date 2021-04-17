package AlarmTests;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.AlarmMethods.Alarm;

public class AlarmTest {
    @Test
    public void justActivateAlarmTest() {
        Alarm alarm = new Alarm();
        alarm.patrol("RIGHT");

        Assert.assertTrue(alarm.isOnPatrol());
    }

    @Test
    public void onAlertModeFromPatrolTest() {
        Alarm alarm = new Alarm();
        alarm.patrol("RIGHT");

        alarm.toAlertMode();

        Assert.assertTrue(alarm.isOnAlertMode());
    }

    @Test
    public void deactivatePatrollingAlarmWithRightCodeTest() {
        Alarm alarm = new Alarm();
        alarm.patrol("RIGHT");

        alarm.deactivate("RIGHT");

        Assert.assertFalse(alarm.isOnPatrol());
    }

    @Test
    public void deactivatePatrollingAlarmWithWrongCodeTest() {
        Alarm alarm = new Alarm();
        alarm.patrol("RIGHT");

        alarm.deactivate("WRONG");

        Assert.assertTrue(alarm.isOnAlertMode());
    }

    @Test
    public void deactivateBeepingAlarmWithRightCodeTest() {
        Alarm alarm = new Alarm();
        alarm.patrol("RIGHT");
        alarm.toAlertMode();
        alarm.deactivate("RIGHT");

        Assert.assertFalse(alarm.isOnPatrol());
    }

    @Test
    public void deactivateBeepingAlarmWithWrongCodeTest() {
        Alarm alarm = new Alarm();
        alarm.patrol("RIGHT");
        alarm.toAlertMode();

        alarm.deactivate("WRONG");

        Assert.assertTrue(alarm.isOnAlertMode());
    }
}