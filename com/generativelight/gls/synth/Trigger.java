package com.generativelight.gls.synth;

/**
 * Created by janne on 20.03.2016.
 */
public class Trigger {

    private final long starttime;
    private final int duration;
    private final int velocity;

    private float age;

    public Trigger(int duration, int velocity) {
        this.starttime = System.currentTimeMillis();
        if (duration <= 0) duration = 1;
        this.duration = duration;
        this.velocity = velocity;
        updateAge();
    }

    public void updateAge() {
        age = ((System.currentTimeMillis() - starttime) / (float)duration);
    }

    public float getAge() { return age; }

    public int getVelocity() { return velocity; }
}
