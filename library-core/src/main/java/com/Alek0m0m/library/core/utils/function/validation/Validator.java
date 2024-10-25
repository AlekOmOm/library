package com.Alek0m0m.library.core.utils.function.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static com.Alek0m0m.library.core.utils.function.validation.Validator.UserInput.isStrongPassword;

// Input Validation

/*

                List<String> filteredTypes = requiredTypes.stream()
                        .filter(type -> List.of("UPPERCASE", "LOWERCASE", "DIGIT", "SPECIAL", "MIN_LENGTH").contains(type))
                        .toList();

 */


public class Validator {
    public static final Predicate<String> isNonEmpty = str -> str != null && !str.trim().isEmpty();
    public static final Predicate<Integer> isPositive = num -> num > 0;
    public static final Predicate<List<String>> listIsNonEmpty = str -> str.stream().anyMatch(isNonEmpty);


    public static class UserInput {
        // Constants
        public static final List<String> DEFAULT_REQUIREMENTS = List.of("UPPERCASE", "LOWERCASE", "DIGIT", "SPECIAL", "MIN_LENGTH");

        private static final Pattern upperCasePattern = Pattern.compile("[A-Z]");
        private static final Pattern lowerCasePattern = Pattern.compile("[a-z]");
        private static final Pattern digitPattern = Pattern.compile("[0-9]");
        private static final Pattern specialCharPattern = Pattern.compile("[@#$%^&+=]");

        private static final Map<String, Predicate<String>> DEFAULT_REQ_PATTERNS = new HashMap<>(Map.of(
                "UPPERCASE", (pwd) -> upperCasePattern.matcher(pwd).find(),
                "LOWERCASE", (pwd) -> lowerCasePattern.matcher(pwd).find(),
                "DIGIT", (pwd) -> digitPattern.matcher(pwd).find(),
                "SPECIAL", (pwd) -> specialCharPattern.matcher(pwd).find(),
                "MIN_LENGTH", (pwd) -> pwd.length() >= 8
        ));

        // ---------------------------- Operations ----------------------------

        public static final Predicate<String> isValidUsername = name -> name.matches("^[a-zA-Z0-9]{5,15}$");
        public static final Predicate<String> isValidPassword = pwd -> pwd.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");
        public static final Predicate<String> isEmail = email -> email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$");

        public static final BiPredicate<String, List<String>> isStrongPassword = (pwd, choiceOfDefaultRequirements) ->
                isValidPassword.test(pwd) &&
                        choiceOfDefaultRequirements.isEmpty()
                        ? RequiredTypes.validatePassword(pwd, DEFAULT_REQUIREMENTS)
                        : RequiredTypes.validatePassword(pwd, choiceOfDefaultRequirements);


        // ---------------------------- class Required Types ----------------------------
        public static class RequiredTypes {


            // ---------------------------- Main Operation ----------------------------
            public static boolean validatePassword(String pwd, List<String> requirements) { // system admin chosen requirements
                if (requirements.isEmpty()) requirements = DEFAULT_REQUIREMENTS;

                List<String> validRequirements = VALID_REQUIREMENTS_FILTER.apply(requirements);

                for (String requirement : validRequirements) {
                    if (!DEFAULT_REQ_PATTERNS.get(requirement).test(pwd)) {
                        System.out.println("Debug validatePassword");
                        System.out.println(" - " + requirement + " failed!");
                        return false;
                    }
                }

                return true;
            }





            // ---------------------------- Helper Methods ----------------------------
            private static final Function<List<String>, List<String>> VALID_REQUIREMENTS_FILTER = requiredTypes -> requiredTypes.stream()
                        .filter(DEFAULT_REQUIREMENTS::contains)
                        .toList();


        }
    }

    static class API {




    }

    private static boolean isValidPasswordImpl(String pwd) {
        return UserInput.isValidPassword.test(pwd);
    }



    public static void main(String[] args) {
        // test validatePassword through isPasswordStrong
        String pwd = "Password1@";
        String badPwd = "password";
        List<String> requirements = List.of("UPPERCASE", "LOWERCASE", "DIGIT", "SPECIAL", "MIN_LENGTH");
        System.out.println("test validatePassword");
        System.out.println(" 1. test: " + pwd);
        System.out.println("result isPasswordStrong? = "+ isStrongPassword.test(pwd, requirements));
        System.out.println(" 2. test: " + badPwd);
        System.out.println("result isPasswordStrong? = "+ isStrongPassword.test(badPwd, requirements));
    }

}
