package ru.sbt.mipt.oop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.Adapters.CCEventHandlerAdapter;
import ru.sbt.mipt.oop.CommandSenders.CommandSender;
import ru.sbt.mipt.oop.CommandSenders.FakeCommandSender;
import ru.sbt.mipt.oop.HouseServants.*;
import ru.sbt.mipt.oop.Readers.SmartHomeReader;
import ru.sbt.mipt.oop.Readers.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.SensorMethods.SensorEventType;
import ru.sbt.mipt.oop.com.coolcompany.smarthome.events.SensorEventsManager;

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

    @Autowired
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
    @Autowired
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
}
