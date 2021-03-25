package ru.sbt.mipt.oop;

import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.*;

public class SmartHomeReaderJSON implements SmartHomeReader {
    public SmartHome getSmartHome() throws Exception {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("C://Users/Роман/IdeaProjects/SmartHome/src/main/java/ru/sbt/mipt/oop/SmartHome.json")));
        return gson.fromJson(json, SmartHome.class);
    }
}
