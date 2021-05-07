package ru.sbt.mipt.oop.RemoteControllers;

import ru.sbt.mipt.oop.ExternalSources.rc.RemoteControl;
import ru.sbt.mipt.oop.RemoteControllers.Commands.Command;

import java.util.HashMap;

public class RemoteControlImpl implements RemoteControl {
    private final String rcId;
    private final RemoteControl nextRemoteControl;
    private final HashMap<String, Command> buttonCodeToCommand;

    public RemoteControlImpl(String rcId, RemoteControl nextRemoteControl, HashMap<String, Command> buttonCodeToCommand) {
        this.rcId = rcId;
        this.nextRemoteControl = nextRemoteControl;
        this.buttonCodeToCommand = buttonCodeToCommand;
    }

    public void addCommand(String buttonCode, Command command) {
        buttonCodeToCommand.put(buttonCode, command);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (rcId.equals(this.rcId) && buttonCodeToCommand.containsKey(buttonCode)) {
            buttonCodeToCommand.get(buttonCode).execute();
        }
        if (nextRemoteControl != null){
            nextRemoteControl.onButtonPressed(buttonCode, rcId);
        }
    }
}
