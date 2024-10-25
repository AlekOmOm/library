package com.Alek0m0m.library.core.utils.function.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;

// Input Validation

/*

                List<String> filteredTypes = requiredTypes.stream()
                        .filter(type -> List.of("UPPERCASE", "LOWERCASE", "DIGIT", "SPECIAL", "MIN_LENGTH").contains(type))
                        .toList();

 */


public class Validator {
    public static final Predicate<String> isNonEmpty = str -> str != null && !str.trim().isEmpty();
    public static final Predicate<Integer> isPositive = num -> num > 0;
    public final Predicate<List<String>> listIsNonEmpty = str -> str.stream().anyMatch(isNonEmpty);

    private final List<String> input = new ArrayList<>();
    private final String username;
    private final String password;
    private final String email;

    public Validator (String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        input.addAll(List.of(username, password, email));
    }

    public boolean validateUserInfo(){
        return listIsNonEmpty.test(input);
    }



    public static class UserInput {
        private static final Pattern upperCasePattern = Pattern.compile("[A-Z]");
        private static final Pattern lowerCasePattern = Pattern.compile("[a-z]");
        private static final Pattern digitPattern = Pattern.compile("[0-9]");
        private static final Pattern specialCharPattern = Pattern.compile("[@#$%^&+=]");

        public static final Predicate<String> isEmail = email -> email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$");
        public static final Predicate<String> isValidPassword = pwd -> pwd.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");
        public static final Predicate<String> isValidUsername = name -> name.matches("^[a-zA-Z0-9]{5,15}$");


        public static final Predicate<String> isPasswordStrongStd = (pwd) -> isValidPassword.test(pwd) && RequiredTypes.isRequiredTypesPresent.test(pwd);

        public static final BiPredicate<String, List<String>> isPasswordStrongPredicate = (pwd, types) ->
                isValidPassword.test(pwd) &&
                        RequiredTypes.isRequiredTypesPresent(pwd, types);






        // ----- Helper ----

        public static class RequiredTypes {

            private static final List<String> DEFAULT_REQUIRED_TYPES = List.of("UPPERCASE", "LOWERCASE", "DIGIT", "SPECIAL", "MIN_LENGTH");


            public static void main(String[] args) {
                List<String> list = new ArrayList<>( List.of("John","UPPERCASE") );
                String password = "JOHN";
                //System.out.println(isPasswordStrong.apply(password, list));
                System.out.println(filterRequiredTypes(list));

            }


            public static final Predicate<String> isRequiredTypesPresent = pwd -> isRequiredTypesPresent(pwd, new ArrayList<String>());

            public static boolean isRequiredTypesPresent(String pwd, List<String> requiredTypes) {
                if (requiredTypes.isEmpty()) {
                    requiredTypes = DEFAULT_REQUIRED_TYPES;
                }

                List<String> requiredTypesFiltered = filterRequiredTypes(requiredTypes);

                List<String> filteredTypes = requiredTypes.stream()
                        .filter(type -> List.of("UPPERCASE", "LOWERCASE", "DIGIT", "SPECIAL", "MIN_LENGTH").contains(type))
                        .toList();


                return false;
            }

            private static List<String> filterRequiredTypes(List<String> requiredTypes) {
                return requiredTypes.stream()
                        .filter(DEFAULT_REQUIRED_TYPES::contains)
                        .toList();
            }

        }
    }

    static class API {




    }

    private static boolean isValidPasswordImpl(String pwd) {
        return UserInput.isValidPassword.test(pwd);
    }



}
