package com.generativelight.gls.stage;

import com.generativelight.gls.stage.devicegroups.OPCGroup;
import com.generativelight.gls.stage.devices.Device;
import com.generativelight.gls.stage.devices.OPCDevice;
import com.generativelight.gls.stage.devicegroups.DeviceGroup;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The StageBuilder builds up stages from config files and stores Stages in config files.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class StageBuilder {

    /**
     * Reads the given config file and creates a stage object with its devices and device groups.
     * @param config config file path
     * @return the builded stage
     */
    public static Stage build(String config, PApplet papplet) {
        Stage stage = new Stage();

        try {
            JSONObject json = JSONObject.parse(readFile(config));
            JSONArray devices = json.getJSONArray("devices");
            JSONArray groups  = json.getJSONArray("groups");

            stage.setName(json.getString("name"));
            stage.setDimLevel(json.getFloat("dimLevel"));

            HashMap<Integer, Device> deviceMap = new HashMap<>();

            // create devices
            for (int i = 0; i < devices.size(); i++) {
                JSONObject deviceJson = devices.getJSONObject(i);
                Device device = null;
                switch (deviceJson.getString("type")) {
                    case "OPCDevice": device = new OPCDevice(deviceJson, papplet); break;
                }
                if (device != null) {
                    stage.addDevice(device);
                    deviceMap.put(deviceJson.getInt("id"), device);
                } else {
                    System.out.println("StageBuilder: Unknown device type: " + deviceJson.getString("type") + ", this config entry will be ignored");
                }
            }

            // create device groups
            for (int i = 0; i < groups.size(); i++) {
                JSONObject groupJson = groups.getJSONObject(i);
                DeviceGroup group = null;
                switch (groupJson.getString("type")) {
                    case "OPCGroup": group = new OPCGroup(groupJson, deviceMap, papplet); break;
                }
                if (group != null) {
                    stage.addDeviceGroup(group);
                } else {
                    System.out.println("StageBuilder: Unknown device group type: " + groupJson.getString("type") + ", this config entry will be ignored");
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage() +
                    "StageBuilder: Error while parsing the stage config file.\n" +
                    "Maybe a json syntax error.\n" +
                    "Your stage will not be complete!"
            );
        }
        return stage;
    }

    public static void store(Stage stage) {
        //TODO: writes back the stage to config file of the stage
    }

    public static void store(Stage stage, String config) {
        //TODO: writes back the stage to a given config file
    }

    /**
     * Reads the content of a file into a string.
     * @param path the path to file
     * @return the content of the file as a string
     */
    private static String readFile(String path) {
        String fileContent = "";
        Scanner scanner = null;
        try {
            scanner = new Scanner( new File(path), "UTF-8" );
            fileContent = scanner.useDelimiter("\\A").next();

        } catch (Exception e) {
            System.out.println(
                    "StageBuilder: Error on reading config file.\n" +
                    "Maybe wrong path to config file?"
            );
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return fileContent;
    }
}
