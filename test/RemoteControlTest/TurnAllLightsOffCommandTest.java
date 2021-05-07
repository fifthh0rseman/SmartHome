package RemoteControlTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.ExternalSources.rc.RemoteControl;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.SmartHome;

import static org.junit.Assert.*;

public class TurnAllLightsOffCommandTest {
    private static final AbstractApplicationContext context
            = new AnnotationConfigApplicationContext(RemoteControlTestConfiguration.class);
    @Test
    public void execute() {
        RemoteControl remoteControl = context.getBean(RemoteControl.class);
        remoteControl.onButtonPressed("B", "Light Controller");
        context.getBean(SmartHome.class).execute(lightCandidate ->{
            if (lightCandidate instanceof Light){
                Light light = (Light) lightCandidate;
                Assert.assertFalse(light.isOn());
            }
        });
    }
}