package ru.sbt.mipt.oop.RemoteControllers.Commands;

import ru.sbt.mipt.oop.HouseContainment.Light;
import ru.sbt.mipt.oop.SmartHome;

public class TurnAllLightsOffCommand implements Command {
    private final SmartHome smartHome;

    public TurnAllLightsOffCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(lightCandidate ->
        {
            if (lightCandidate instanceof Light) {
                Light light = (Light) lightCandidate;
                light.setOn(false);
            }
        });
    }
}
