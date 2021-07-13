package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.exception.ResourceNotFoundException;
import com.rookie.webwatch.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    private User user = new User("user21", "user@gmail.com","123");

    @Test
    public void getUser(){
        when(userRepository.findAll()).thenReturn(Stream.of(new User("user21", "user@gmail.com","123"),
                new User("user22", "user@gmail.com","123")).collect(Collectors.toList()));
        Assert.assertEquals(2,userService.retrieveUsers().size());
    }

    @Test
    public void addUser() throws Exception{
        when(userRepository.save(any())).thenReturn(user);

        Assert.assertEquals("user21", user.getUserName());
    }

    @Test
    public void updateCate(){
        User user = new User("user21", "user@gmail.com","123");
        when(userRepository.findById(user.getUser_id())).thenReturn(Optional.of(user));

        when(userRepository.save(any())).thenReturn(user);

        Assert.assertEquals("user21", user.getUserName());
    }

    @Test
    public void deleteCate() throws ResourceNotFoundException {
        User user = new User("user21", "user@gmail.com","123");
        when(userRepository.findById(user.getUser_id())).thenReturn(Optional.of(user));

        userService.deleteUser(user.getUser_id());

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void loadCateById(){
        // Given
        User user = new User();
        user.setUser_id(20L);
        user.setUserName("username 20");
        user.setUserPassword("password");
        user.setUserEmail("user20@gmail.com");

        when(userRepository.findById(user.getUser_id())).thenReturn(java.util.Optional.of(user));

        Assert.assertEquals("username 20",user.getUserName());

    }
}
