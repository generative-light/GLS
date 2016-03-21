package com.generativelight.gls.gfx;

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
        shader = new PShader(Synth.getSynth().getPApplet());
        shader.setFragmentShader(path);

        parameters = new ArrayList<GLShaderParameter>();
    }

    public void update(float alpha, float age) {
        for (GLShaderParameter parameter : parameters) {
            //shader.set( parameter.getName(), parameter.getValue() );
        }
        shader.set( "time", System.currentTimeMillis()/1000.0f );
    }

    public void draw(PGraphics image, float alpha, float age) {

        shader.set( "resolution", image.width, image.height, 1.0f );
        image.shader( shader );
    }
}
