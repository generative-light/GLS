package com.generativelight.gls.stage.devices;


import processing.core.PGraphics;

/**
 * Abstract class for a device that displays images e.g. an opc server.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public abstract class ImageDevice extends Device {

    protected PGraphics image;

    /**
     * Get the image of this ImageDevice to draw on it.
     * @return the image
     */
    public PGraphics getImage() { return image; }
}
