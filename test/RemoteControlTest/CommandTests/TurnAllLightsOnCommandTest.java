package RemoteControlTest.CommandTests;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.ExternalSources.rc.RemoteControl;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.SmartHome;

public class TurnAllLightsOnCommandTest {
    private static final AbstractApplicationContext context
            = new AnnotationConfigApplicationContext(RemoteControlTestConfiguration.class);
    @Test
    public void execute() {
        RemoteControl remoteControl = context.getBean(RemoteControl.class);
        remoteControl.onButtonPressed("C", "Test Controller");
        context.getBean(SmartHome.class).execute(lightCandidate ->{
            if (lightCandidate instanceof Light){
                Light light = (Light) lightCandidate;
                Assert.assertTrue(light.isOn());
            }
        });
    }
}