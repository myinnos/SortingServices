package com.omnify.hire.extras;

import java.util.List;

/**
 * Created by myinnos on 08/09/17.
 */

public class Helper {

    public static int[] buildIntArray(List<String> integers) {
        int[] ints = new int[integers.size()];
        int i = 0;
        for (String n : integers) {
            ints[i++] = Integer.parseInt(n.trim());
        }
        return ints;
    }
}
