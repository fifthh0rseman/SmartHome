package RemoteControlTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.ExternalSources.rc.RemoteControl;
import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.SmartHome;

import static org.junit.Assert.*;

public class TurnHallLightOnCommandTest {
    private static final AbstractApplicationContext context
            = new AnnotationConfigApplicationContext(RemoteControlTestConfiguration.class);
    @Test
    public void execute() {
        RemoteControl remoteControl = context.getBean(RemoteControl.class);
        remoteControl.onButtonPressed("A", "Hall Controller");
        context.getBean(SmartHome.class).execute(lightCandidate ->{
            if (lightCandidate instanceof Light){
                Light light = (Light) lightCandidate;

                if (light.getId().equals("L0")) {
                    Assert.assertTrue(light.isOn());
                }
                if (light.getId().equals("L1")) {
                    Assert.assertTrue(light.isOn());
                }
            }
        });
    }
}