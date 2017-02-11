package com.henterprise.note.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Retrieved from:
 * <a href>http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string</a>
 *
 * @author Howard.
 */

public final class SessionIdentifierGenerator {

    private static SecureRandom random = new SecureRandom();

    public static String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}