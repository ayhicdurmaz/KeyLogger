package Utility;

import java.util.regex.Pattern;

public class StringValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    public static boolean isValidEmail(String s) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(s).matches();
    }

    public static boolean isStringOfInt(String s) {
        return s.matches("\\d+");
    }
}
