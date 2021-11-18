package RemoteControlTest.ControlTests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.ExternalSources.rc.RemoteControl;
import ru.sbt.mipt.oop.RemoteControllers.Builders.RemoteControlBuilder;
import ru.sbt.mipt.oop.RemoteControllers.Builders.RemoteControlImplBuilder;
import ru.sbt.mipt.oop.RemoteControllers.Commands.Command;
import ru.sbt.mipt.oop.RemoteControllers.RemoteControlImpl;

@Configuration
public class DummyRemoteControlTestConfiguration {
    @Bean
    RemoteControl remoteControl() {
        RemoteControlBuilder<RemoteControlImpl> builder = new RemoteControlImplBuilder();
        builder.setRcId("TestRemote");
        builder.bindButton("A", createDummyCommand("TestRemoteA"));
        return builder.buildRemoteControl();
    }

    Command createDummyCommand(String code) {
        return () -> {
            throw new RuntimeException(code);
        };
    }
}
