package uz.pdp.online.lesson10.task2.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfoValidator {
    public static boolean isValidName(String name) {
        // Name should contain only letters, spaces, and may include hyphens or apostrophes
        String nameRegex = "^[A-Za-z\\s'-]+$";
        return name.matches(nameRegex);
    }

    public static boolean isValidEmail(String email) {
        // Email validation using a regular expression
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
