package com.generativelight.gls.synth;

import com.generativelight.gls.gfx.ColorPalette;
import processing.core.PGraphics;
import java.util.ArrayList;

/**
 * The Cue is a collection of layers with its own description for the alpha envelope and optional an own color palette.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class Cue {

    private ArrayList<Layer> layerList;

    private Easing.Type alphaInCurve;
    private Easing.Type alphaOutCurve;
    private float curveSwitch;

    private ColorPalette colorPalette;

    private Slot slot;

    protected Cue() {
        layerList = new ArrayList<>();
        alphaInCurve = Easing.Type.LINEAR_IN;
        alphaOutCurve = Easing.Type.LINEAR_IN;
        curveSwitch = 0.5f;

        //testing
        Layer layer = new Layer("marble.json");
        addLayer(layer);
    }

    protected void setSlot(Slot slot) {
        this.slot = slot;
    }

    public ColorPalette getColorPalette() {
        if (colorPalette == null) {
            return slot.getColorPalette();
        } else {
            return colorPalette;
        }
    }

    protected void draw(PGraphics image, Trigger trigger) {
        float age   = trigger.getAge();
        float alpha = Easing.getValue(alphaInCurve, alphaOutCurve, age, curveSwitch, 0.0f, (float)(trigger.getVelocity() / 127.0));

        for (Layer layer : layerList) {
            layer.draw(image, alpha, age);
        }
    }

    private void addLayer(Layer layer) { layerList.add(layer); }
    private void addLayer(Layer layer, int index) {
        layerList.add(index, layer);
    }

    private void removeLayer(int index) {
        if ((index >= 0) && (index < layerList.size())) {
            layerList.remove(index);
        }
    }
}
