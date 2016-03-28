package com.generativelight.gls.synth;

import com.generativelight.gls.gfx.ColorPalette;
import com.generativelight.gls.gfx.GLShader;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * A Layer ...
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class Layer {

    private Cue cue;
    private ColorPalette colorPalette;
    private int primaryColorIndex = 0;
    private int secondaryColorIndex = 0;
    private int blendMode;              // uses the PConstants ADD BLEND ...
    private GLShader shader;

    public Layer() {

        //testing
        blendMode = PConstants.ADD;
        shader = new GLShader("test");

    }

    protected void setCue(Cue cue) {
        this.cue = cue;
        if (colorPalette == null) {
            this.colorPalette = cue.getColorPalette();
        }
    }

    protected void draw(PGraphics image, float alpha, float age) {
        image.beginDraw();
        image.blendMode(blendMode);
        shader.draw(image, alpha, age);
        image.endDraw();
    }
}
