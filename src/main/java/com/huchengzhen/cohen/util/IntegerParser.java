package com.huchengzhen.cohen.util;

import java.util.Optional;

public class IntegerParser {
    public static Optional<Integer> parseInt(String toParse) {
        try {
            return Optional.of(Integer.parseInt(toParse));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
