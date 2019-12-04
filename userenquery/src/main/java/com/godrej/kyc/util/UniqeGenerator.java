package com.godrej.kyc.util;

import java.security.SecureRandom;
import java.util.Random;

public class UniqeGenerator {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static String createRandomInteger() {
		long aStart = 100000000000000L;
		long aEnd = 999999999999999L;
		Random aRandom = new Random();
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		long range = aEnd - (long) aStart + 1;
		long fraction = (long) (range * aRandom.nextDouble());
		long randomNumber = fraction + (long) aStart;
		return String.valueOf(randomNumber);

	}
}
