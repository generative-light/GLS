package com.generativelight.gls.synth;

/**
 * The Easing class calculates values based on easings.
 *
 * Easing methods are taken from Robert Penner, who licensed them under the BSD License.
 * https://github.com/jesusgollonet/processing-penner-easing
 * http://www.opensource.org/licenses/bsd-license.php
 *
 */
public class Easing {
    /**
     * Defines the easing functions which are available
     */
    public enum Type {
        LINEAR_IN,
        SINE_IN, SINE_OUT, SINE_INOUT,
        CIRC_IN, CIRC_OUT, CIRC_INOUT,
        QUART_IN, QUART_OUT, QUART_INOUT,
        EXPO_IN, EXPO_OUT, EXPO_INOUT,
        BACK_IN, BACK_OUT, BACK_INOUT
    }

    /**
     * Calculates a value depending on the easing, the age and the start and end values.
     *
     * @param type Type of the easing
     * @param age float value between 0.0 and 1.0 tells where we are in the easing process
     * @param start where does the value begins at age 0.0
     * @param end where does the value goes at age 1.0
     *
     * @return the calculated value between start and end
     */
    public static float getValue(Type type, float age, float start, float end) {
        return start + getDiffValue(type, age, -(start-end));
    }

    /**
     * Calculates a value depending on the easing, the age and the start and end values.
     * After curveSwitch the same easing is used to go back to the start value.
     *
     * @param inCurve Type of the easing
     * @param age float value between 0.0 and 1.0 tells where we are in the easing process
     * @param curveSwitch the age where we want to get the end value, from here on it goes back to the start value
     * @param start where does the value begins at age 0.0 and ends at age = 1.0
     * @param end where does the value goes at age = curveSwitch
     *
     * @return the calculated value between start and end
     */
    public static float getValue(Type inCurve, float age, float curveSwitch, float start, float end) {
        return getValue(inCurve, inCurve, age, curveSwitch, start, end);
    }

    /**
     * Calculates a value depending on the easings, the age and the start and end values.
     * After curveSwitch the outCurve is used to go back to the start value.
     *
     * @param inCurve Type of the easing for age = 0.0 -> age < curveSwitch
     * @param outCurve Type of the easing for age = curveSwitch -> age = 1.0
     * @param age float value between 0.0 and 1.0 tells where we are in the easing process
     * @param curveSwitch the age where we want to get the end value, from here on it goes back to the start value
     * @param start where does the value begins at age 0.0 and ends at age = 1.0
     * @param end where does the value goes at age = curveSwitch
     *
     * @return the calculated value between start and end
     */
    public static float getValue(Type inCurve, Type outCurve, float age, float curveSwitch, float start, float end) {
        if (age < curveSwitch) return getValue(inCurve, (age / curveSwitch),  start, end);
        return getValue(outCurve, ((age - curveSwitch) / (1 - curveSwitch)), end, start);

    }

    /**
     * Internal method for calculating a value between 0 and diff depending on an easing.
     * This method is written for easily handle start and end values external in the negative area and for start values which are bigger than the end values.
     *
     * @param type Type of the easing
     * @param age float value between 0.0 and 1.0 tells where we are in the easing process
     * @param diff the value where we are at age = 1.0, start value is always 0.0
     *
     * @return the calculated value between 0.0 and diff
     */
    private static float getDiffValue(Type type, float age, float diff) {
        switch (type) {
            case LINEAR_IN:     return linearIn(age, diff);
            case SINE_IN:       return sineIn(age, diff);
            case SINE_OUT:      return sineOut(age, diff);
            case SINE_INOUT:    return sineInOut(age, diff);
            case CIRC_IN:       return circIn(age, diff);
            case CIRC_OUT:      return circOut(age, diff);
            case CIRC_INOUT:    return circInOut(age, diff);
            case QUART_IN:      return quartIn(age, diff);
            case QUART_OUT:     return quartOut(age, diff);
            case QUART_INOUT:   return quartInOut(age, diff);
            case EXPO_IN:       return expoIn(age, diff);
            case EXPO_OUT:      return expoOut(age, diff);
            case EXPO_INOUT:    return expoInOut(age, diff);
            case BACK_IN:       return backIn(age, diff);
            case BACK_OUT:      return backOut(age, diff);
            case BACK_INOUT:    return backInOut(age, diff);
            default:            return linearIn(age, diff);
        }
    }


    /**
     * The easing mehtods themselves are following.
     */

    private static float linearIn(float age, float diff) {
        return age * diff;
    }

    private static float sineIn(float age, float diff) {
        return  -diff * (float)Math.cos(age * (Math.PI/2)) + diff;
    }

    private static float sineOut(float age, float diff) {
        return   diff * (float)Math.sin(age * (Math.PI/2));
    }

    private static float sineInOut(float age, float diff) {
        return -diff/2 * ((float)Math.cos(Math.PI * age) - 1);
    }

    private static float circIn(float age, float diff) {
        return  -diff * ((float)Math.sqrt(1 - age * age) - 1);
    }

    private static float circOut(float age, float diff) {
        return  diff * (float)Math.sqrt(1 - (age - 1) * (age - 1));
    }

    private static float circInOut(float age, float diff) {
        if ((age*=2) < 1) return -diff/2 * ((float)Math.sqrt(1 - age * age) - 1);
        return  diff/2 * ((float)Math.sqrt(1 - (age - 2) * (age - 2)) + 1);
    }

    private static float quartIn(float age, float diff) {
        return diff * age * age * age * age;
    }

    private static float quartOut(float age, float diff) {
        return -diff * ((age - 1) * (age - 1) * (age - 1) * (age - 1) - 1);
    }

    private static float quartInOut(float age, float diff) {
        if ((age *= 2) < 1) return diff / 2 * age * age * age * age;
        return -diff / 2 * ((age -= 2 ) * age * age * age - 2);
    }

    private static float expoIn(float age, float diff) {
        return (age == 0) ? 0.0f : diff * (float)Math.pow(2, 10 * (age - 1));
    }

    private static float expoOut(float age, float diff) {
        return (age == 1) ? diff : diff * (-(float)Math.pow(2, -10 * age) + 1);
    }

    private static float expoInOut(float age, float diff) {
        if (age == 0) return 0;
        if (age == 1) return diff;
        if ((age *= 2) < 1) return diff/2 * (float)Math.pow(2, 10 * (age - 1));
        return diff/2 * (-(float)Math.pow(2, -10 * --age) + 2);
    }

    private static float backIn(float age, float diff) {
        float s = 1.70158f;
        return diff * age * age * ((s + 1) * age - s);
    }

    private static float backOut(float age, float diff) {
        float s = 1.70158f;
        return diff * ((age -= 1) *  age * ((s + 1) * age + s) + 1);
    }

    private static float backInOut(float age, float diff) {
        float s = 1.70158f;
        if ((age *= 2) < 1) return diff / 2 * (age * age *(((s *= 1.525f) + 1)* age - s));
        return diff / 2 * ((age -= 2) * age * (((s *= 1.525f) + 1) * age + s) + 2);
    }
}
