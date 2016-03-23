package com.generativelight.gls.gfx;

import com.generativelight.gls.synth.Easing;
import com.generativelight.gls.synth.Synth;
import processing.core.PGraphics;
import processing.opengl.PShader;

import java.util.ArrayList;

/**
 * Created by janne on 21.03.2016.
 */
public class GLShader {

    private PShader shader;
    private ArrayList<GLShaderParameter> parameters;

    public GLShader(String path) {
        //shader = new PShader(Synth.getSynth().getPApplet());
        //shader.setFragmentShader(path);
        parameters = new ArrayList<GLShaderParameter>();

        //testing
        shader = Synth.getSynth().getPApplet().loadShader("marble.glsl");
        parameters.add(new GLShaderParameter("red", 0.5f, 1.0f));
        parameters.add(new GLShaderParameter("green", 0.0f, 0.0f));
        parameters.add(new GLShaderParameter("blue", 1.0f, 0.5f));
        //parameters.add(new GLShaderParameter("noiselvl", 0.1f, 2.0f));
        //parameters.add(new GLShaderParameter("count", 1.0f, 10.0f));

    }

    private void update(float alpha, float age) {
        for (GLShaderParameter parameter : parameters) {
            shader.set( parameter.getName(), Easing.getValue(Easing.Type.CIRC_IN, age, parameter.getStartValue(), parameter.getEndValue()) );
        }
        shader.set("time", System.currentTimeMillis()/1000.0f);
        shader.set("alpha", alpha);
    }

    public void draw(PGraphics image, float alpha, float age) {
        update(alpha, age);
        shader.set( "resolution", image.width, image.height, 1.0f );
        image.background(0, 0);
        image.shader( shader );
        image.rect( 0, 0, image.width, image.height );
    }
}
