import com.Alek0m0m.library.spring.web.mvc.BaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InitDataTestSpring {

    @InjectMocks
    private InitData initData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        //initData = new InitData(userService, userMapper);
    }

//    @Test
//    void shouldInitUsers() {
//        // Given
//        doNothing().when(userService).save(any(UserDTO.class));
//
//        // When
//        initData.initUsers();
//
//        // Then
//        List<UserDTO> usersRepo = userService.findAll();
//        List<UserDTO> users = Arrays.stream(initData.users)
//                .map(userMapper::map)
//                .toList();
//
//        assertTrue(usersRepo.containsAll(users));
//        verify(userService, times(initData.users.length)).save(any(UserDTO.class));
//    }
}