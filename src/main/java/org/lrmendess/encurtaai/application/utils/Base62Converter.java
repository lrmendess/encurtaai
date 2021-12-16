package org.lrmendess.encurtaai.application.utils;

public final class Base62Converter {
    
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = ALPHABET.length();

    public static String fromBase10(long value)
    {
        StringBuilder base62 = new StringBuilder();

        do {
            int mod = (int) (value % BASE);
            base62.append(ALPHABET.charAt(mod));
            value /= BASE;
        } while (value > 0);

        return base62.reverse().toString();
    }

    public static long toBase10(final String value)
    {
        long base10 = 0L;
        char[] arr = new StringBuilder(value).reverse().toString().toCharArray();

        for (int i = arr.length - 1; i >= 0; i--) {
            base10 += ALPHABET.indexOf(arr[i]) * (long) Math.pow(BASE, i);
        }

        return base10;
    }

}
