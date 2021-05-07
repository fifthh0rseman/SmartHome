package RemoteControlTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.AlarmMethods.Alarm;
import ru.sbt.mipt.oop.ExternalSources.rc.RemoteControl;

import static org.junit.Assert.*;

public class AlarmToAlertModeCommandTest {
    private static final AbstractApplicationContext context
            = new AnnotationConfigApplicationContext(RemoteControlTestConfiguration.class);
    @Test
    public void execute(){
        RemoteControl remoteControl = context.getBean(RemoteControl.class);
        remoteControl.onButtonPressed("A", "Alarm Controller");
        Assert.assertTrue(context.getBean(Alarm.class).isOnAlertMode());
    }
}