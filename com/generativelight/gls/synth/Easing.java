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
    public enum Type { LINEAR_IN, SINE_IN, SINE_OUT, SINE_INOUT, CIRC_IN, CIRC_OUT, CIRC_INOUT};

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
     * After curveSwitch the same easing is used to go back to the start value.
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

}
