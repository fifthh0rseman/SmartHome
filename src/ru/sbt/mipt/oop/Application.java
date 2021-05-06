package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.com.coolcompany.smarthome.events.SensorEventsManager;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        SensorEventsManager manager = applicationContext.getBean(SensorEventsManager.class);
        manager.start();
    }
}
