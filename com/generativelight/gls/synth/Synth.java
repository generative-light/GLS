package com.generativelight.gls.synth;

import com.generativelight.gls.stage.StageBuilder;
import com.generativelight.gls.stage.Stage;
import com.generativelight.gls.synth.midi.MIDIInput;
import com.generativelight.gls.gfx.ColorPalette;
import processing.core.PApplet;
import java.util.ArrayList;

/**
 * The Synth is an interface between the stage, the MIDI input and the drawing parts.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class Synth {

    private static Synth instance = null;
    private static int[] defaultColors = {};

    final static int SLOT_COUNT = 1;

    private ArrayList<Slot> slots;
    private MIDIInput midiInput;
    private Stage stage;
    private PApplet papplet;
    private ColorPalette colorPalette;

    public static Synth getSynth() {
        if (instance == null) System.out.println("Create Synth before calling getSynth()!");
        return instance;
    }

    /**
     * Constructor for Creating the Synth.
     * @param papplet the processing parent
     */
    public Synth(PApplet papplet) {

        this.papplet = papplet;

        slots = new ArrayList<>();
        for (int i = 0; i < SLOT_COUNT; i++) {
            slots.add(new Slot(papplet));
        }

        midiInput = new MIDIInput(this);

        stage = StageBuilder.build("C:\\Users\\janne\\Desktop\\stage.json", papplet);

        colorPalette = new ColorPalette(defaultColors);

        instance = this;
    }

    /**
     * Get a Slot by its channel number.
     * @param index the index or midi channel (1-8)
     * @return the queried Slot
     */
    public Slot getSlot(int index) {
        if (index > 0 && index <= SLOT_COUNT) {
            return slots.get(index-1);
        }
        else return null;
    }

    public ColorPalette getColorPalette() { return colorPalette; }

    public PApplet getPApplet() { return papplet; }

    /**
     * Calls the synth methods of all slots
     */
    public void synth() {
        for (Slot slot : slots) {
            slot.synth();
        }
        stage.updateStage();
    }

    /**
     * Returns the stage.
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

}
