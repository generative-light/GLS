package com.generativelight.gls.synth;

import com.generativelight.gls.synth.midi.Trigger;
import processing.core.PGraphics;
import java.util.ArrayList;

/**
 * The Cue ...
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class Cue {

    private ArrayList<Layer> layers;
    private PGraphics outImage;


    protected Cue() {
        layers = new ArrayList<>();
    }

    protected void resizeSlotImage(PGraphics image) {
        image.setSize(outImage.width, outImage.height);
    }

    protected void trigger(Trigger trigger) {    }

    protected void setOutImage(PGraphics outImage) {
        this.outImage = outImage;
    }

    protected void draw(PGraphics image) {
        image.beginDraw();
        for (Layer layer : layers) {
            layer.draw(image);
        }
        image.endDraw();

        outImage.beginDraw();
        outImage.image(image, outImage.width/2, outImage.height/2);
        outImage.endDraw();
    }
}
