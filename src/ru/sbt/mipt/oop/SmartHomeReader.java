package ru.sbt.mipt.oop;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

public class SmartHomeReader {
    public SmartHome getSmartHome() throws Exception {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        return gson.fromJson(json, SmartHome.class);
    }
}
