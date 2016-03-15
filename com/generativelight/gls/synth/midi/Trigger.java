package com.generativelight.gls.synth.midi;

/**
 * A Trigger holds information from the MIDI packet
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class Trigger {
    private long startTime;
    private int velocity;
    private int pitch;

    public Trigger(long startTime, int velocity, int pitch) {
        this.startTime = startTime;
        this.velocity = velocity;
        this.pitch = pitch;
    }

    public long getStartTime() { return startTime; }
    public long getVelocity() { return velocity; }
    public long getPitch() { return pitch; }
}
