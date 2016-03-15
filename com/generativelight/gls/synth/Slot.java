package com.generativelight.gls.synth;

import com.generativelight.gls.synth.midi.Trigger;
import processing.core.PApplet;
import processing.core.PGraphics;
import java.util.ArrayList;

/**
 * A slot is a channel on the MIDI bus. It has an image where the active Cue draws on .
 * In the end of every render process the image is drawn to the CUE bases outImage which is a device or group on the stage.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class Slot {

    private ArrayList<Cue> cues;
    private Cue activeCue;
    private PApplet papplet;
    private PGraphics image;
    private int triggerLength = 0;



    protected Slot(PApplet papplet) {
        cues = new ArrayList<>();
        //TODO: add final int for DIMENSION
        image = papplet.createGraphics(128, 128);
    }

    protected void draw() {
        activeCue.draw(image);

        //cleanup after drawing
        image.background(0);
    }

    protected void addCue(Cue cue) {
        cues.add(cue);
    }

    protected void selectCue(int cue) {
        if ((cue >= 0) && (cue < cues.size())) {
            activeCue = cues.get(cue);
            activeCue.resizeSlotImage(image);
        }
    }

    protected void controllerChange(int value) {
        // do something with the value
    }

    protected void trigger(Trigger trigger) {
        activeCue.trigger(trigger);
    }


}
