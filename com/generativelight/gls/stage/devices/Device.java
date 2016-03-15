package com.generativelight.gls.stage.devices;


/**
 * Abstract base class for a device on the stage.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public abstract class Device {

    protected String name;

    /**
     * Updates the real device behind this class.
     */
    public abstract void update(float dimLevel);

    /**
     * Returns the name
     * @return name of this device
     */
    public String getName() {
        return name;
    }

    public String toString() { return getName(); }
}
