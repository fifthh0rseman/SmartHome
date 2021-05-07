package RemoteControlTest;

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

        builder.setRcId("Hall Controller");
        builder.bindButton("A", new TurnHallLightOnCommand(smartHome));
        builder.bindButton("B", new HallDoorCloseCommand(smartHome));
        builder.buildRemoteControl();

        builder.setRcId("Light Controller");
        builder.bindButton("A", new TurnAllLightsOnCommand(smartHome));
        builder.bindButton("B", new TurnAllLightsOffCommand(smartHome));
        builder.buildRemoteControl();

        builder.setRcId("Alarm Controller");
        builder.bindButton("A", new AlarmToAlertModeCommand(smartHome));
        builder.bindButton("B", new AlarmActivateCommand(smartHome, "4555"));
        return builder.buildRemoteControl();
    }
}
