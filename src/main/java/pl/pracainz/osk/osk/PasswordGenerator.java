package pl.pracainz.osk.osk;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
	
	private static final Random RANDOM_NUMBER = new SecureRandom();
    private static final String NUMBERS_AND_LETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
     
    public static String generatePassword(int length) {
        StringBuilder generatedPassword = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            generatedPassword.append(NUMBERS_AND_LETTERS.charAt(RANDOM_NUMBER.nextInt(NUMBERS_AND_LETTERS.length())));
        }
        return new String(generatedPassword);
    }
}
