package com.Alek0m0m.library.core.utils.function.validation;

import java.util.ArrayList;
import java.util.List;
import static com.Alek0m0m.library.core.utils.function.validation.Validator.UserInput.RequiredTypes.isRequiredTypesPresent;
import static com.Alek0m0m.library.core.utils.function.validation.Validator.addUser;
import static com.Alek0m0m.library.core.utils.function.validation.Validator.listIsNonEmpty;


public class UserValidator {
    private final List<String> input = new ArrayList<>();
    private final String username;
    private final String password;
    private final String email;

    public UserValidator(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        input.addAll(List.of(this.username, this.password, this.email));

        Validator.addUser(this);
    }

    public boolean validate(){
        return listIsNonEmpty.test(input); // TODO: change to implement all default User info validation of Validator
    }

    public void delete() {
        Validator.deleteUser(this);
    }

    // toString
}
