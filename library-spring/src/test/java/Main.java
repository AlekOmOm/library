import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Main {

    @Autowired
    private Dependencies dependencies;
    @Autowired
    private InitData initData;

    // Refactor the main method to be non-static
    public void execute(String[] args) {
        UserDTOInput userDTOInput = new UserDTOInput()
                .setName("John Doe");

        UserDTO userDTO = new UserMapper().toDTO(userDTOInput);

        System.out.println(userDTO.getName());

        dependencies.userService.save(userDTO);

        System.out.println(dependencies.userService.findById(1L).getName());
    }

    // The entry point of your program should only serve to bootstrap your Spring application
    public static void main(String[] args){
        //ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //Main main = context.getBean(Main.class);
        //main.execute(args);
    }

}

@Service
class Dependencies {

    public UserService userService;

    @Autowired
    public Dependencies(UserService userService) {
        this.userService = userService;
    }

}