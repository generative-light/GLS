package com.generativelight.gls.synth;

import com.generativelight.gls.synth.utils.ColorPalette;
import processing.core.PGraphics;

/**
 * A Layer ...
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class Layer {

    private Cue cue;
    private ColorPalette colorPalette;


    public Layer() {

    }

    protected void setCue(Cue cue) {
        this.cue = cue;
        this.colorPalette = cue.getColorPalette();
    }

    protected void draw(PGraphics image, float alpha, float age) {

    }
}
