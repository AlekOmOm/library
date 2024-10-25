
import com.Alek0m0m.library.core.utils.function.validation.Validator;

import java.util.ArrayList;
import java.util.List;

import static com.Alek0m0m.library.core.utils.function.validation.Validator.UserInput.RequiredTypes.enforcePasswordPolicy;
import static com.Alek0m0m.library.core.utils.function.validation.Validator.UserInput.isStrongPassword;
import static com.Alek0m0m.library.core.utils.function.validation.Validator.listIsNonEmpty;

import com.Alek0m0m.library.core.utils.function.validation.UserValidator;

public class RequiredTypesTest {

    public static void main(String[] args) {
        String username = "bob";
        String password = "bob";
        String mail = "bob";

        String usernameStrong = "Bob12345";
        String passwordStrong = "Bob12345@";
        String mailStrong = "bob@mail.com";

        UserValidator newUser = new UserValidator
                .Builder(username, password)
                .email(mail)
                .build();

        UserValidator newUserStrong = new UserValidator
                .Builder(usernameStrong, passwordStrong)
                .email(mailStrong)
                .build();

        // tests

        newUser.validate();
        newUserStrong.validate();

        System.out.println();
        isStrongTest(password, username);
        isStrongTest(passwordStrong, username);
    }

    static int counter = 0;
    private static void isStrongTest(String password, String username) {
        counter++;
        System.out.println(counter +". "+ password);
        if (isStrongPassword.test(password, new ArrayList<String>())) {
            System.out.println(" password passed the tests!");
            System.out.println("  chosen by: " + username);
        }
    }
}
