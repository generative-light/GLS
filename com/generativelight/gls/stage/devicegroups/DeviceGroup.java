package com.generativelight.gls.stage.devicegroups;

import java.util.ArrayList;

/**
 * This class groups devices together to get a single interface to interact with multiple devices like interacting with one.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
abstract public class DeviceGroup {

    protected String name;

    /**
     * Constructor
     */
    protected DeviceGroup() {
        this.name = "Unnamed Group";
    }

    /**
     * Sets the name of the group.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the group.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifies the devices in this group
     * Note that the update method of all devices is called in updateStage()!
     * Don't call the update method of the devices here! Only update values or images in the devices.
     */
    public abstract void update();
}
