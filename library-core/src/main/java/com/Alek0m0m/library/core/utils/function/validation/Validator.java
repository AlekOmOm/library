package com.Alek0m0m.library.core.utils.function.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.logging.Filter;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Input Validation



public class Validator {
    public static final Predicate<String> isNonEmpty = str -> str != null && !str.trim().isEmpty();
    public static final Predicate<Integer> isPositive = num -> num > 0;




    static class UserInput {
        private static final Pattern upperCasePattern = Pattern.compile("[A-Z]");
        private static final Pattern lowerCasePattern = Pattern.compile("[a-z]");
        private static final Pattern digitPattern = Pattern.compile("[0-9]");
        private static final Pattern specialCharPattern = Pattern.compile("[@#$%^&+=]");

        public static final Predicate<String> isEmail = email -> email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$");
        public static final Predicate<String> isValidPassword = pwd -> pwd.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");
        public static final Predicate<String> isValidUsername = name -> name.matches("^[a-zA-Z0-9]{5,15}$");


        public static final Predicate<String> isPasswordStrongStd = (pwd) -> isValidPassword.test(pwd) && RequiredTypes.isRequiredTypesPresent(pwd);

        public static final BiPredicate<String, List<String>> isPasswordStrong = (pwd, requiredTypes) -> isValidPassword.test(pwd) && RequiredTypes.isRequiredTypesPresent(pwd, requiredTypes);





        // ----- Helper ----

        static class RequiredTypes {
            private static final List<String> DEFAULT_REQUIRED_TYPES = List.of("UPPERCASE", "LOWERCASE", "DIGIT", "SPECIAL", "MIN_LENGTH");


            private static boolean isRequiredTypesPresent(String pwd) {
                return isRequiredTypesPresent(pwd, DEFAULT_REQUIRED_TYPES);
            }

            private static boolean isRequiredTypesPresent(String pwd, List<String> requiredTypes) {
                if (requiredTypes.isEmpty()) {
                    requiredTypes = DEFAULT_REQUIRED_TYPES;
                }

                Stream<String> requiredTypesFiltered = requiredTypes.stream()
                        .filter(DEFAULT_REQUIRED_TYPES::contains)                      ;



                return false;
            }

        }
    }

    static class API {




    }

    private static boolean isValidPasswordImpl(String pwd) {
        return UserInput.isValidPassword.test(pwd);
    }



}
