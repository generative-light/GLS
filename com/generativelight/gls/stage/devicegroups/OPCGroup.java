package com.generativelight.gls.stage.devicegroups;

import com.generativelight.gls.stage.devices.OPCDevice;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.data.JSONObject;
import java.util.HashMap;

/**
 * An OPCGroup groups multiple OPCDevices to one image together.
 * You can draw on the group image and the group handles internally the slicing and drawing of the image to the single devices.
 * OPCDevices are centered in their grid row
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class OPCGroup extends ImageDeviceGroup {

    /**
     * Constructor
     * @param name the name of the group
     * @param papplet the PApplet for creating PGraphics
     */
    public OPCGroup(String name, PApplet papplet) {
        super(name, papplet);
    }

    /**
     * Constructor
     * Creates an OPCGroup from a json representation of it
     * @param json the json config object
     * @param deviceMap a HashMap of devices with their id from the config as key
     * @param papplet the PApplet for creating PGraphics
     */
    public OPCGroup(JSONObject json, HashMap deviceMap, PApplet papplet) {
        super(json, deviceMap, papplet);
    }

    /**
     * Returns the number of led tubes in this group horizontally.
     * This is not the real number calculated by devices * tubes per device!
     * When this group has 3 devices a row you will get 3 * tubes per device.
     * @return the number of tubes horizontally in this group
     */
    public int getTubeCount() { return grid.getWidth() * OPCDevice.TUBE_COUNT; }

    /**
     * Creates the image of the group
     */
    protected void createImage() {
        image = papplet.createGraphics((grid.getWidth() * OPCDevice.IMAGE_DIMENSION), (grid.getHeight() * OPCDevice.IMAGE_DIMENSION));
        image.imageMode(PConstants.CENTER);
        image.beginDraw();
        image.background(0);
        image.endDraw();
    }

    /**
     * Updates the images of the devices in this group.
     * The group image is sliced in part images which are drawn to the device images.
     */
    public void update() {
        OPCDevice opcDevice;
        PGraphics deviceImage;
        PImage slicedImage;

        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidthOfRow(row); col++) {

                opcDevice = (OPCDevice)grid.get(row, col);
                if (opcDevice != null) {
                    try {
                        // change the x-offset to make this left- or right-handed, at this point only centered is implementd
                        slicedImage = image.get((col * OPCDevice.IMAGE_DIMENSION + (grid.getWidth() - grid.getWidthOfRow(row)) * OPCDevice.IMAGE_DIMENSION / 2), (row * OPCDevice.IMAGE_DIMENSION), OPCDevice.IMAGE_DIMENSION, OPCDevice.IMAGE_DIMENSION);

                        deviceImage = opcDevice.getImage();
                        deviceImage.beginDraw();
                        deviceImage.image(slicedImage, deviceImage.width/2, deviceImage.height/2);
                        deviceImage.endDraw();
                    } catch (Exception e) {
                        /*
                        empty catch block. NullPointerException will be thrown if nothing was drawn on the group image. Then we don't draw to the devices. That's it!
                         */
                    }
                }
            }
        }
    }
}
