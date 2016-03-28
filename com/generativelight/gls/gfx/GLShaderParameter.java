package com.generativelight.gls.gfx;

import com.generativelight.gls.synth.Easing;

/**
 * A GLShaderParameter holds information for a single parameter of a shader.
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class GLShaderParameter {

    private String name;
    private float startValue;
    private float endValue;
    private float curveSwitch;
    private Easing.Type inCurve;
    private Easing.Type outCurve;

    public GLShaderParameter(String name, float startValue, float endValue, float curveSwitch, Easing.Type inCurve, Easing.Type outCurve) {
        this.name = name;
        this.startValue = startValue;
        this.endValue = endValue;
        this.curveSwitch = curveSwitch;
        this.inCurve = inCurve;
        this.outCurve = outCurve;
    }

    public String getName() { return name; }
    public float getStartValue() { return startValue; }
    public float getEndValue() { return endValue; }
    public float getCurveSwitch() { return curveSwitch; }
    public Easing.Type getinCurve() { return inCurve; }
    public Easing.Type getoutCurve() { return outCurve; }
}
