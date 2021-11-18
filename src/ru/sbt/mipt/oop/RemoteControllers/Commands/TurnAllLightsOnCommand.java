package ru.sbt.mipt.oop.RemoteControllers.Commands;

import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.SmartHome;

public class TurnAllLightsOnCommand implements Command {
    private final SmartHome smartHome;

    public TurnAllLightsOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object ->
        {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(true);
            }
        });
    }
}
