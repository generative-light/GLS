package com.generativelight.gls.synth;

import com.generativelight.gls.synth.utils.ColorPalette;
import processing.core.PGraphics;
import java.util.ArrayList;

/**
 * The Cue ...
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

        image.beginDraw();
        for (Layer layer : layerList) {
            layer.draw(image, alpha, age);
        }
        image.endDraw();
    }

    private void addLayer(Layer layer, int index) {
        layerList.add(index, layer);
    }

    private void removeLayer(int index) {
        if ((index >= 0) && (index < layerList.size())) {
            layerList.remove(index);
        }
    }
}
