import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class MainTest {

    public static void main(String[] args) {


        // TODO: can't pass repo in UserService constructor, since its an interface


        UserController userController = new UserController(new UserService(new UserRepository(), new UserEntityToDTOMapper()));


        // Test of User MVC

        // 0. init User in repository


        // 1. Front to Back
        UserDTO userDTO = new UserDTO();
        userDTO.setName("name");




    }
}
