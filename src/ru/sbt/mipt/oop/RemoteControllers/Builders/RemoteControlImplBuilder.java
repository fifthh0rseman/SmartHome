package ru.sbt.mipt.oop.RemoteControllers.Builders;

import ru.sbt.mipt.oop.RemoteControllers.Commands.Command;
import ru.sbt.mipt.oop.RemoteControllers.RemoteControlImpl;

import java.util.HashMap;

public class RemoteControlImplBuilder implements RemoteControlBuilder<RemoteControlImpl>{
    private HashMap<String, Command> buttonCodeToCommand;
    private RemoteControlImpl prevRemoteControl;
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
        prevRemoteControl = new RemoteControlImpl(rcId, prevRemoteControl, buttonCodeToCommand);
        this.reset();
        return prevRemoteControl;
    }
}
