package com.generativelight.gls.gfx;

import com.generativelight.gls.synth.Easing;

/**
 * Created by janne on 21.03.2016.
 */
public class GLShaderParameter {

    private String name;
    private float start;
    private float end;
    private Easing.Type inCurve;
    private Easing.Type outCurve;


    public GLShaderParameter(String name, float start, float end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName() { return name; }
    public float getStartValue() { return start; }
    public float getEndValue() { return end; }
}
