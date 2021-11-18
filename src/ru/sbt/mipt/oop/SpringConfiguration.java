package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.Adapters.CCEventHandlerAdapter;
import ru.sbt.mipt.oop.AlarmMethods.Alarm;
import ru.sbt.mipt.oop.CommandSenders.CommandSender;
import ru.sbt.mipt.oop.CommandSenders.FakeCommandSender;
import ru.sbt.mipt.oop.HouseServants.*;
import ru.sbt.mipt.oop.Readers.SmartHomeReader;
import ru.sbt.mipt.oop.Readers.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.RemoteControllers.Commands.*;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.ExternalSources.com.coolcompany.smarthome.events.SensorEventsManager;

import java.util.List;
import java.util.Map;

@Configuration
public class SpringConfiguration {
    @Bean
    public SmartHomeReader smartHomeReader() {
        return new SmartHomeReaderJSON();
    }

    @Bean
    public SmartHome smartHome (SmartHomeReader reader) throws Exception {
        return reader.getSmartHome();
    }

    @Bean
    public Map<String, SensorEventType> ccEventToSensorEvent () {
        return Map.of(
                "LightIsOn", SensorEventType.LIGHT_ON,
                "LightIsOff", SensorEventType.LIGHT_OFF,
                "DoorIsOpen", SensorEventType.DOOR_OPEN,
                "DoorIsClosed", SensorEventType.DOOR_CLOSED
        );
    }

    @Bean
    public SensorEventsManager manager (List<EventHandler> eventHandlers, SmartHome smartHome) {
        SensorEventsManager manager = new SensorEventsManager();
        manager.registerEventHandler(new CCEventHandlerAdapter(
                new TheHouseGuardDecorator(eventHandlers), smartHome, ccEventToSensorEvent()));
        return manager;
    }

    @Bean
    public EventHandler lightEventHandler() {
        return new LightEventHandler();
    }

    @Bean
    public CommandSender commandSender() {
        return new FakeCommandSender();
    }

    @Bean
    public EventHandler hallDoorEventHandler() {
        return new HallDoorEventHandler(commandSender());
    }

    @Bean
    public EventHandler doorEventHandler() {
        return new DoorEventHandler();
    }

    @Bean
    public EventHandler alarmEventHandler() {
        return new AlarmEventHandler();
    }

    @Bean
    public Command alarmActivateCommand(SmartHome smartHome) {
        return new AlarmActivateCommand(smartHome, "");
    }

    @Bean
    public Command alarmToAlertModeCommand(SmartHome smartHome) {
        return new AlarmToAlertModeCommand(smartHome);
    }

    @Bean
    public Command hallDoorCloseCommand(SmartHome smartHome) {
        return new HallDoorCloseCommand(smartHome);
    }

    @Bean
    public Command turnHallLightOnCommand(SmartHome smartHome) {
        return new TurnHallLightOnCommand(smartHome);
    }

    @Bean
    public Command turnLightOnCommand(SmartHome smartHome) {
        return new TurnAllLightsOnCommand(smartHome);
    }

    @Bean
    public Command turnLightOffCommand(SmartHome smartHome) {
        return new TurnAllLightsOffCommand(smartHome);
    }

    @Bean
    public Alarm alarm(SmartHome smartHome) {
        return smartHome.getAlarm();
    }
}
