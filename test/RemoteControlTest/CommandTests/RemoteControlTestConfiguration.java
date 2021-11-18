package RemoteControlTest.CommandTests;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.HouseContainment.Door;
import ru.sbt.mipt.oop.RemoteControllers.Builders.RemoteControlBuilder;
import ru.sbt.mipt.oop.RemoteControllers.Builders.RemoteControlImplBuilder;
import ru.sbt.mipt.oop.RemoteControllers.Commands.*;
import ru.sbt.mipt.oop.RemoteControllers.RemoteControlImpl;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SpringConfiguration;

import java.util.concurrent.atomic.AtomicReference;

@Configuration
@ComponentScan
@Import(SpringConfiguration.class)
public class RemoteControlTestConfiguration {
    @Bean
    @Autowired
    RemoteControlImpl remoteControl(SmartHome smartHome) {
        RemoteControlBuilder<RemoteControlImpl> builder = new RemoteControlImplBuilder();
        builder.setRcId("Test Controller");
        builder.bindButton("A", new TurnHallLightOnCommand(smartHome));
        builder.bindButton("B", new HallDoorCloseCommand(smartHome));
        builder.bindButton("C", new TurnAllLightsOnCommand(smartHome));
        builder.bindButton("D", new TurnAllLightsOffCommand(smartHome));
        builder.bindButton("1", new AlarmToAlertModeCommand(smartHome));
        builder.bindButton("2", new AlarmActivateCommand(smartHome, "4555"));
        return builder.buildRemoteControl();
    }
}
