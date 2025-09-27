package me.brody.mazesurvival.utils;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeralUtils {
    private static final Map<Integer, String> romanNumeralByInt;

    static {
        romanNumeralByInt = new HashMap<>();
        romanNumeralByInt.put(1, "I");
        romanNumeralByInt.put(2, "II");
        romanNumeralByInt.put(3, "III");
        romanNumeralByInt.put(4, "IV");
        romanNumeralByInt.put(5, "V");
        romanNumeralByInt.put(6, "VI");
        romanNumeralByInt.put(7, "VII");
        romanNumeralByInt.put(8, "VIII");
        romanNumeralByInt.put(9, "IX");
        romanNumeralByInt.put(10, "X");
    }

    public static String romanNumeralOf(int num) {
        return romanNumeralByInt.get(num);
    }
}
