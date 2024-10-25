package com.Alek0m0m.library.core.utils.function.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/*
 Validator class (with UserInput & RequiredTypes)
   - note: so far basic implementation, but structure set for easy addition of more validation methods

  Use cases:
    - used in UserValidator, which validates user input for registration

   Features:
    simple validation
    - isNonEmpty
    - isPositive
    - listIsNonEmpty

    UserInput:
    - Validate username (alphanumeric, 5-15 characters)
    - Validate password (8 characters, at least 1 uppercase, 1 lowercase, 1 digit, 1 special character)
    - Validate email (standard email format)
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

        public static final Predicate<String> isValidUsername = name -> name.matches("^[a-zA-Z0-9]{5,15}$"); // 5-15 characters, alphanumeric only, æøå not allowed
        public static final Predicate<String> isValidUsername2 = name -> name.matches("^[a-zA-Z0-9æøåÆØÅ]{5,15}$"); // 5-15 characters, alphanumeric only, æøå allowed
        public static final Predicate<String> isValidPassword = pwd -> pwd.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");
        public static final Predicate<String> isEmail = email -> email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$");

        public static final BiPredicate<String, List<String>> isStrongPassword = (pwd, subsetDefaultReqs) ->
                isValidPassword.test(pwd) && (
                        subsetDefaultReqs.isEmpty()
                        ? RequiredTypes.enforcePasswordPolicy(pwd, DEFAULT_REQUIREMENTS) // enforce all default requirements
                        : RequiredTypes.enforcePasswordPolicy(pwd, subsetDefaultReqs)); // enforce chosen requirements

        // ---------------------------- class Required Types ----------------------------
        public static class RequiredTypes {

            // ---------------------------- Main Operation ----------------------------
            public static boolean enforcePasswordPolicy(String pwd, List<String> requirements) { // chosen requirements to enforce (default or subset of default reqs)
                System.out.println("debug enforcePasswordPolicy:");

                return !VALID_REQUIREMENTS_FILTER.apply(requirements) // filter out invalid requirements
                        .stream()
                        .map(req -> testPwd(pwd, req)) // test each requirement
                        .toList().contains(false); // if any test fails, return false == not strong
            }

            private static boolean testPwd(String pwd, String req) {
                boolean boo = DEFAULT_REQ_PATTERNS.get(req).test(pwd);
                if (!boo) {
                    System.out.println("PassWord: " + pwd);
                    System.out.println(" - " + req + " failed!");
                } else {
                    System.out.println(" - " + req + " passed!");
                }
                return boo;
            }


            // ---------------------------- Helper Methods ----------------------------
            private static final Function<List<String>, List<String>> VALID_REQUIREMENTS_FILTER = requiredTypes -> requiredTypes.stream()
                        .filter(DEFAULT_REQUIREMENTS::contains)
                        .toList();


        }
    }
}
