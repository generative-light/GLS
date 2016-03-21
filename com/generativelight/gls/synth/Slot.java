package com.generativelight.gls.synth;

import com.generativelight.gls.gfx.ColorPalette;
import processing.core.PApplet;
import processing.core.PGraphics;
import java.util.ArrayList;

/**
 * A slot holds a list of cues. One cue is the active cue and gets the triggers from the slot to draw something.
 * At the end of every render process the image is drawn to the outImages which are images of devices or groups on the stage.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class Slot {

    private final static int SLOT_IMAGE_DIMENSION = 256;

    private ArrayList<Cue> cueList;
    private Cue activeCue;

    private PGraphics image;
    private ArrayList<PGraphics> outImages;

    private Trigger trigger;

    private ColorPalette colorPalette;

    protected Slot(PApplet papplet) {
        cueList = new ArrayList<>();
        image = papplet.createGraphics(SLOT_IMAGE_DIMENSION, SLOT_IMAGE_DIMENSION);
        outImages = new ArrayList<>();
        trigger = null;

        //testing
        Cue cue = new Cue();
        addCue(cue);
        selectCue(0);
    }

    protected void addCue(Cue cue) {
        cueList.add(cue);
        cue.setSlot(this);
    }

    protected void removeCue(int index) {
        if ((index >= 0) && (index < cueList.size())) {
            cueList.remove(index);
        }
    }

    protected void selectCue(int cue) {
        if ((cue >= 0) && (cue < cueList.size())) {
            activeCue = cueList.get(cue);
        }
    }

    protected void addOutImage(PGraphics outImage) { outImages.add(outImage); }

    protected void removeOutImage(int index) {
        if ((index >= 0) && (index < outImages.size())) {
            outImages.remove(index);
        }
    }

    protected void controllerChange(int value) {
        // do something with the value
    }

    public ColorPalette getColorPalette() {
        if (colorPalette == null) {
            return Synth.getSynth().getColorPalette();
        } else {
            return colorPalette;
        }
    }

    public void trigger(Trigger trigger) {
        if (this.trigger != null) System.out.println("retrigger...");
        this.trigger = trigger;
    }

    protected void synth() {
        if (trigger != null) {
            trigger.updateAge();
            if (trigger.getAge() <= 1.0f) {
                if (activeCue != null) {
                    //activeCue.draw(image, trigger);
                    drawToOutImages();
                    clearImage();
                }
            } else {
                trigger = null;
            }
        }
    }

    private void drawToOutImages() {
        for (PGraphics outImage : outImages) {
            outImage.beginDraw();
            outImage.image(image, outImage.width/2, outImage.height/2, outImage.width, outImage.height);
            image.endDraw();
        }
    }

    private void clearImage() {
        image.beginDraw();
        image.background(0,0);
        image.endDraw();
    }

    public float getStatus() {
        if (trigger == null) {
            return 0.0f;
        } else {
            return trigger.getAge();
        }
    }



}
