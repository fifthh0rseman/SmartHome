package ru.sbt.mipt.oop.RemoteControllers.Builders;

import ru.sbt.mipt.oop.RemoteControllers.Commands.Command;

public interface RemoteControlBuilder<T> {
    void reset();

    void setRcId(String rcId);
    void bindButton(String id, Command command);
    T buildRemoteControl();
}
