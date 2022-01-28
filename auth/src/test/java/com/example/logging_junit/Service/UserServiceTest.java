package com.example.logging_junit.Service;

//import com.example.auth.Exception.UserNotFoundException;
import com.example.auth.dao.IUserDao;
import com.example.auth.dao.hibernate.HibernateUserDao;
import com.example.auth.entity.User;
import com.example.auth.service.UserService;
import com.example.auth.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private IUserDao userDao;
    @InjectMocks
    private UserServiceImpl userService;

    static List<User> list;
    static{
        list = new ArrayList<>();
        list.add(User.builder().ID(1).userName("James").email("James@gmail.com").build());
        list.add(User.builder().ID(2).userName("Jim").email("Jim@gmail.com").build());
        list.add(User.builder().ID(3).userName("Jammy").email("Jammy@gmail.com").build());
        list.add(User.builder().ID(4).userName("Jin").email("Jin@gmail.com").build());
    }

    @BeforeEach
    public void setup() throws NullPointerException {


        List<User> users = new ArrayList<>();
        users.add(list.get(0));
        lenient().when(userDao.getUserByName("James")).thenReturn(list.get(0));
        lenient().when(userDao.getUserByName("Joey")).thenReturn(new User());


    }
    @Test
    public void getUserByName() throws NullPointerException{
        assertEquals(list.get(0),userService.getUserByName("James"));
        verify(userDao,times(1)).getUserByName("James");

//        Exception exception = assertThrows(UserNotFoundException.class, () -> userService.getUserByName("Joey"));
//        assertEquals("Joey", exception.getMessage());
//        verify(userDao, times(1)).getUserByName("Joey");
    }
}
