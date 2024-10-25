
import com.Alek0m0m.library.core.utils.function.validation.Validator;

import java.util.ArrayList;
import java.util.List;

import static com.Alek0m0m.library.core.utils.function.validation.Validator.UserInput.RequiredTypes.isRequiredTypesPresent;

public class RequiredTypesTest {

    public static void main(String[] args) {
        String username = "bob";
        String password = "bob";
        String mail = "bob";

        Validator io = new Validator(username, password, mail);
        if (io.listIsNonEmpty.test(List.of(username,password,mail))) {
            System.out.println("nice, not empty");
            System.out.println("validate():"+ io.validateUserInfo());
        }
        
        Validator.isNonEmpty.test(String.valueOf("bob"));

        if (isRequiredTypesPresent(password, new ArrayList<String>())) {
            System.out.println("heyo");
        }
    }
}
