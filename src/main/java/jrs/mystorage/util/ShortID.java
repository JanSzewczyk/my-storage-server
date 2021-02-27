package jrs.mystorage.util;

import org.apache.commons.lang3.RandomStringUtils;

public class ShortID {

    public static String randomShortID() {
        return String.format(
                "%s%s%s",
                RandomStringUtils.randomAlphabetic(3).toUpperCase() ,
                RandomStringUtils.randomAlphanumeric(4).toUpperCase(),
                RandomStringUtils.randomNumeric(3)
        );
    }
}
