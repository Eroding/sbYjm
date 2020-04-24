package com.cnh.util;

/**
 * Base64 工具类
 */
public class Base64Util {
    private static final char[] ALPHABET;
    private static final char last2byte;
    private static final char last4byte;
    private static final char last6byte;
    private static final char lead6byte;
    private static final char lead4byte;
    private static final char lead2byte;
    private static final char[] encodeTable;
    private static int[] toInt;

    static {
        ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        last2byte = (char)Integer.parseInt("00000011", 2);
        last4byte = (char)Integer.parseInt("00001111", 2);
        last6byte = (char)Integer.parseInt("00111111", 2);
        lead6byte = (char)Integer.parseInt("11111100", 2);
        lead4byte = (char)Integer.parseInt("11110000", 2);
        lead2byte = (char)Integer.parseInt("11000000", 2);
        encodeTable = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        Base64Util.toInt = new int[128];
        for (int i = 0; i < Base64Util.ALPHABET.length; ++i) {
            Base64Util.toInt[Base64Util.ALPHABET[i]] = i;
        }
    }
    public Base64Util() {
    }

    public static String encode(byte[] from) {
        StringBuilder to = new StringBuilder((int) ((double) from.length * 1.34D) + 3);
        int num = 0;
        char currentByte = 0;

        int i;
        for (i = 0; i < from.length; ++i) {
            for (num %= 8; num < 8; num += 6) {
                switch (num) {
                    case 0:
                        currentByte = (char) (from[i] & lead6byte);
                        currentByte = (char) (currentByte >>> 2);
                    case 1:
                    case 3:
                    case 5:
                    default:
                        break;
                    case 2:
                        currentByte = (char) (from[i] & last6byte);
                        break;
                    case 4:
                        currentByte = (char) (from[i] & last4byte);
                        currentByte = (char) (currentByte << 2);
                        if (i + 1 < from.length) {
                            currentByte = (char) (currentByte | (from[i + 1] & lead2byte) >>> 6);
                        }
                        break;
                    case 6:
                        currentByte = (char) (from[i] & last2byte);
                        currentByte = (char) (currentByte << 4);
                        if (i + 1 < from.length) {
                            currentByte = (char) (currentByte | (from[i + 1] & lead4byte) >>> 4);
                        }
                }

                to.append(encodeTable[currentByte]);
            }
        }

        if (to.length() % 4 != 0) {
            for (i = 4 - to.length() % 4; i > 0; --i) {
                to.append("=");
            }
        }

        return to.toString();
    }

    public static byte[] decode(final String s) {
        final int delta = s.endsWith("==") ? 2 : (s.endsWith("=") ? 1 : 0);
        final byte[] buffer = new byte[s.length() * 3 / 4 - delta];
        final int mask = 255;
        int index = 0;
        for (int i = 0; i < s.length(); i += 4) {
            final int c0 = Base64Util.toInt[s.charAt(i)];
            final int c2 = Base64Util.toInt[s.charAt(i + 1)];
            buffer[index++] = (byte)((c0 << 2 | c2 >> 4) & mask);
            if (index >= buffer.length) {
                return buffer;
            }
            final int c3 = Base64Util.toInt[s.charAt(i + 2)];
            buffer[index++] = (byte)((c2 << 4 | c3 >> 2) & mask);
            if (index >= buffer.length) {
                return buffer;
            }
            final int c4 = Base64Util.toInt[s.charAt(i + 3)];
            buffer[index++] = (byte)((c3 << 6 | c4) & mask);
        }
        return buffer;
    }
}