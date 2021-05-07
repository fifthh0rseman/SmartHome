package RemoteControlTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.ExternalSources.rc.RemoteControl;
import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.SmartHome;

public class HallDoorCloseCommandTest {
    private static final AbstractApplicationContext context
            = new AnnotationConfigApplicationContext(RemoteControlTestConfiguration.class);
    @Test
    public void execute() {
        RemoteControl remoteControl = context.getBean(RemoteControl.class);
        remoteControl.onButtonPressed("B", "Hall Controller");
        context.getBean(SmartHome.class).execute(doorCandidate -> {
            if (doorCandidate instanceof Door) {
                Door door = (Door) doorCandidate;
                if (door.getId().equals("D0")){
                    Assert.assertFalse(door.isOpen());
                }
            }
        });
    }
}