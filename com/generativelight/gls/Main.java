package com.generativelight.gls;


import com.generativelight.gls.synth.Trigger;

public class Main {

    public static void main(String[] args) {

        Trigger trigger = new Trigger(500, 127);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            trigger.updateAge();

            System.out.println((System.currentTimeMillis() - start) + " : " + trigger.getAge());
            try {
                Thread.sleep(1);
            } catch (Exception e) {}
        }
    }
}
