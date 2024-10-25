package com.Alek0m0m.library.core.utils.function.validation;

import java.util.ArrayList;
import java.util.List;

import static com.Alek0m0m.library.core.utils.function.validation.Validator.UserInput.*;
import static com.Alek0m0m.library.core.utils.function.validation.Validator.UserInput.isValidUsername;
import static com.Alek0m0m.library.core.utils.function.validation.Validator.listIsNonEmpty;


public class UserValidator {
    private final List<String> input = new ArrayList<>();
    private final String username;
    private final String password;
    private String email; // optional

    private UserValidator(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        input.addAll(List.of(this.username, this.password));
        if (builder.email != null) {
            this.email = builder.email;
            input.add(this.email);
        }
    }

    // ---------------------------- Main Operations ----------------------------

    public boolean validate(){
        return listIsNonEmpty.test(input) && // all fields filled
                isValidUsername.test(username) &&
                isValidPassword.test(password) &&
                isStrongPassword.test(password, new ArrayList<>()) &&
                (email.isEmpty() || isEmail.test(email));
    }







    // ---------------------------- Getters ----------------------------

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    // ---------------------------- Builder ----------------------------

    public static class Builder {
        // Required parameters
        private final String username;
        private final String password;
        // Optional parameters
        private String email;

        public Builder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public UserValidator build() {
            return new UserValidator(this);
        }
    }
}
