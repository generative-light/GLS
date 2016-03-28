package com.generativelight.gls.gfx;

import com.generativelight.gls.synth.Easing;
import com.generativelight.gls.synth.Synth;
import processing.core.PGraphics;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.opengl.PShader;

import java.util.ArrayList;

/**
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class GLShader {

    private PShader shader;
    private ArrayList<GLShaderParameter> parameters;

    public GLShader(String path) {
        //shader = new PShader(Synth.getSynth().getPApplet());
        //shader.setFragmentShader(path);
        shader = Synth.getSynth().getPApplet().loadShader(path);
        parameters = new ArrayList<GLShaderParameter>();
    }

    public GLShader(JSONObject json) {
        this(json.getString("path"));

        JSONArray parameterArray = json.getJSONArray("parameters");
        JSONObject parameterJSON;
        for (int i = 0; i < parameterArray.size(); i++) {
            parameterJSON = parameterArray.getJSONObject(i);

            String name = parameterJSON.getString("name");
            float startValue = parameterJSON.getFloat("start-value");
            float endValue = parameterJSON.getFloat("end-value");
            float curveSwitch = parameterJSON.getFloat("curve-switch");
            Easing.Type inCurve = Easing.getType(parameterJSON.getString("in-curve"));
            Easing.Type outCurve = Easing.getType(parameterJSON.getString("out-curve"));

            parameters.add(new GLShaderParameter(name, startValue, endValue, curveSwitch, inCurve, outCurve));
        }
    }

    public void draw(PGraphics image, float alpha, float age) {
        update(alpha, age);
        shader.set( "resolution", image.width, image.height, 1.0f );
        image.shader( shader );
        image.rect( 0, 0, image.width, image.height );
    }

    private void update(float alpha, float age) {
        for (GLShaderParameter parameter : parameters) {
            shader.set( parameter.getName(),
                    Easing.getValue(parameter.getinCurve(), parameter.getoutCurve(), age, parameter.getCurveSwitch(), parameter.getStartValue(), parameter.getEndValue()) );
        }
        shader.set("time", System.currentTimeMillis()/1000.0f);
        shader.set("alpha", alpha);
    }
}
