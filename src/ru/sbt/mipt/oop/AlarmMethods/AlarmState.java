package ru.sbt.mipt.oop.AlarmMethods;

public interface AlarmState {
    void patrol(String code);

    void deactivate(String code);

    void toAlertMode();
}
