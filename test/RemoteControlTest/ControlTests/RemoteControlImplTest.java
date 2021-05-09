package RemoteControlTest.ControlTests;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.ExternalSources.rc.RemoteControl;

import org.junit.Assert;


public class RemoteControlImplTest {
    private final static AbstractApplicationContext context
            = new AnnotationConfigApplicationContext(DummyRemoteControlTestConfiguration.class);
    @Test
    public void testCallRemoteControl(){
        RemoteControl testRemoteControl = context.getBean(RemoteControl.class);
        try {
            testRemoteControl.onButtonPressed("A", "TestRemote");
        } catch (RuntimeException e) {
            Assert.assertEquals("TestRemoteA", e.getMessage());
        }
    }
}
