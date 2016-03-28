package com.generativelight.gls.synth;

/**
 * The Synthesizer interface defines the methods/api of the Synth to control it.
 * Every class from the input package should use only methods defined here to interact with the Synth.
 */
public interface Synthesizer {

    void triggerSlot(int slotIndex, Trigger trigger);
    void setTriggerDurationOfSlot(int slotIndex, int durationInBeats);
    void selectCueInSlot(int slotIndex, int cueIndex);
    void sendLayerCommandInSlot(int slotIndex, LayerCommand command);


}
