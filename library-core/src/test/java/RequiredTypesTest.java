
import com.Alek0m0m.library.core.utils.function.validation.Validator;

import java.util.ArrayList;
import java.util.List;

import static com.Alek0m0m.library.core.utils.function.validation.Validator.UserInput.RequiredTypes.isRequiredTypesPresent;
import static com.Alek0m0m.library.core.utils.function.validation.Validator.listIsNonEmpty;

import com.Alek0m0m.library.core.utils.function.validation.UserValidator;

public class RequiredTypesTest {

    public static void main(String[] args) {
        String username = "bob";
        String password = "bob";
        String mail = "bob";

        UserValidator input = new UserValidator(username, password, mail);
        input.validate();

        if (listIsNonEmpty.test(List.of(username,password,mail))) {
            System.out.println("nice, not empty");
            System.out.println("validate():"+ input.validate());
        }

        Validator.isNonEmpty.test(String.valueOf("bob"));

        if (isRequiredTypesPresent(password, new ArrayList<String>())) {
            System.out.println("heyo");
        }
    }
}
