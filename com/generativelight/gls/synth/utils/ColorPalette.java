package com.generativelight.gls.synth.utils;

import java.util.ArrayList;

/**
 * Created by janne on 21.03.2016.
 */
public class ColorPalette {

    private ArrayList<Integer> colors;

    public ColorPalette() {
        colors = new ArrayList<>();
    }

    public ColorPalette(int[] colors) {
        for (int i = 0; i < colors.length; i++) {
            this.colors.add(colors[i]);
        }
    }

    public void addColor(int color) {
        colors.add(color);
    }

    public int getColor(int index) {
        if (!colors.isEmpty()) {
            int i = index % colors.size();
            if (i < 0) i = 0;
            return colors.get(i);
        } else {
            return 0;
        }
    }

    /*
    public int nextColor(int prevColor) {
        int index = colors.indexOf(Integer.valueOf(prevColor)) + 1;
        if (index >= colors.size()) index = 0;
        return getColor(index);
    }
     */
}
