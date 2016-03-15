package com.generativelight.gls.synth.midi;

import com.generativelight.gls.synth.Synth;


/**
 * The MIDIInput reads incoming MIDI packets and handles the translation to the Synth.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class MIDIInput {

    private Synth synth;

    public MIDIInput(Synth synth) {
        this.synth = synth;
    }
}
