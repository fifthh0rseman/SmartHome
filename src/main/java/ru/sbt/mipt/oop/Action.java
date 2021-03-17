package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.SensorMethods.SensorCommand;

public class Action {
    private final SensorCommand globalCommand;

    public Action(SensorCommand globalCommand) {
        this.globalCommand = globalCommand;
    }

    public SensorCommand getGlobalCommand() {
        return globalCommand;
    }
}
