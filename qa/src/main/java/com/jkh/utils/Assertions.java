package com.jkh.utils;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Assertions {

    public static void compareIfEqual(Object actual, Object expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
