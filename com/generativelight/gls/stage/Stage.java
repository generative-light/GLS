package com.generativelight.gls.stage;

import com.generativelight.gls.stage.devicegroups.DeviceGroup;
import com.generativelight.gls.stage.devices.Device;

import java.util.ArrayList;

/**
 * This class represents a stage and holds a collection of the devices and groups of them.
 * Also a stage has some meta data and values like dim for the whole stage.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class Stage {
    private ArrayList<Device>       devices;
    private ArrayList<DeviceGroup>  deviceGroups;

    private String      name;
    private float       dimLevel = 0.5f;
    private String      config;

    /**
     * Constructor
     */
    public Stage() {
        devices = new ArrayList<>();
        deviceGroups = new ArrayList<>();
    }

    protected void setName(String name) { this.name = name; }
    public String getName() { return name; }

    protected void setConfig(String config) { this.config = config; }
    public String getConfig() { return config; }

    /**
     * Sets the dimLevel for this stage.
     * @param dimLevel float between 0.0 = off and 1.0 = brightest
     */
    public void setDimLevel(float dimLevel) {
        if (dimLevel < 0.0) {
            dimLevel = 0.0f;
        } else if (dimLevel > 1.0) {
            dimLevel = 1.0f;
        }
        this.dimLevel = dimLevel;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public ArrayList<DeviceGroup> getDeviceGroups() {
        return deviceGroups;
    }

    /**
     * Adds a device to the devices list.
     * @param device Device that should be added.
     */
    protected void addDevice(Device device) {
        devices.add(device);
    }

    /**
     * Removes a given device from the device list.
     * @param device Device that should be removed.
     */
    protected void removeDevice(Device device) {
        devices.remove(device);
    }

    /**
     * Adds a devicegroup to the group list.
     * @param group DeviceGroup that should be added.
     */
    protected void addDeviceGroup(DeviceGroup group) {
        deviceGroups.add(group);
    }

    /**
     * Removes a given group from the group list.
     * @param group DeviceGroup that should be removed.
     */
    protected void removeDeviceGroup(DeviceGroup group) {
        deviceGroups.remove(group);
    }

    /**
     * Updates the whole stage with all its devices and groups.
     */
    public void updateStage() {
        for (DeviceGroup group : deviceGroups) {
            group.update();
        }
        for (Device device : devices) {
            device.update(dimLevel);
        }
    }
}
