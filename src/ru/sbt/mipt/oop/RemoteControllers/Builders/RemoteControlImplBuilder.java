package ru.sbt.mipt.oop.RemoteControllers.Builders;

import ru.sbt.mipt.oop.RemoteControllers.Commands.Command;
import ru.sbt.mipt.oop.RemoteControllers.RemoteControlImpl;

import java.util.HashMap;
import java.util.Map;

public class RemoteControlImplBuilder implements RemoteControlBuilder<RemoteControlImpl>{
    private Map<String, Command> buttonCodeToCommand;
    private String rcId; //remote control ID

    public RemoteControlImplBuilder() {
        buttonCodeToCommand = new HashMap<>();
    }

    @Override
    public void reset() {
        buttonCodeToCommand = new HashMap<>();
    }

    @Override
    public void setRcId(String rcId) {
        this.rcId = rcId;
    }

    @Override
    public void bindButton(String id, Command command) {
        buttonCodeToCommand.put(id, command);
    }

    @Override
    public RemoteControlImpl buildRemoteControl() {
        RemoteControlImpl remoteControl = new RemoteControlImpl(rcId, buttonCodeToCommand);
        this.reset();
        return remoteControl;
    }
}
