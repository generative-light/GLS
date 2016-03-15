package com.generativelight.gls.stage.devicegroups;

import com.generativelight.gls.stage.devicegroups.utils.DynamicGrid;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.HashMap;

/**
 * An ImageDeviceGroup groups multiple ImageDevices to one ImageDevice together.
 * You can draw on the group image and the group handles internally the slicing of the image to the single devices.
 * ImageDevices are layed together on a grid.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public abstract class ImageDeviceGroup extends DeviceGroup {

    protected PApplet papplet;
    protected PGraphics image;
    protected DynamicGrid grid;

    /**
     * Constructor
     * @param name the name of the group
     * @param papplet the PApplet for creating PGraphics
     */
    protected ImageDeviceGroup(String name, PApplet papplet) {
        this.name = name;
        this.papplet = papplet;
        this.grid = new DynamicGrid();
        createImage();
    }

    /**
     * Constructor
     * Creates an ImageDeviceGroup from a json representation of it
     * @param json the json config object
     * @param deviceMap a HashMap of devices with their id from the config as key
     * @param papplet the PApplet for creating PGraphics
     */
    protected ImageDeviceGroup(JSONObject json, HashMap deviceMap, PApplet papplet) {
        this(   json.getString("name"),
                papplet
        );

        JSONArray devices = json.getJSONArray("devices");
        this.grid = new DynamicGrid(devices, deviceMap);
        createImage();
    }

    /**
     * Get the image of the group to draw on it
     * @return the image of the group
     */
    public PGraphics getImage() { return image; }

    /**
     * Creates the image of the group
     */
    protected abstract void createImage();
}
