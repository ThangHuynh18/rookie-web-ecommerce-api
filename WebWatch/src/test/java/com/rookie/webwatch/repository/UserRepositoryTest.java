package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void createUser(){
        User user = new User();
        user.setUser_id(20L);
        user.setUserName("username 20");
        user.setUserPassword("password");
        user.setUserEmail("user20@gmail.com");
        //user.setRoles("user");

        Assert.assertNotNull(userRepository.save(user));
    }

    @Test
    public void GivenGetAllUserShouldReturnListOfAllUsers(){
        User user1 = new User("user21", "user@gmail.com","123");
        User user2 = new User("user22", "user@gmail.com","123");

        userRepository.save(user1);
        userRepository.save(user2);
        List<User> userList = userRepository.findAll();
        assertEquals("user21", userList.get(9).getUserName());
    }

    @Test
    public void givenIdThenShouldReturnUserOfThatId() {
        User user1 = new User("user23", "user@gmail.com","123");
        User user = userRepository.save(user1);
        Optional<User> optional =  userRepository.findById(user.getUser_id());
        assertEquals(user.getUser_id(), optional.get().getUser_id());
        assertEquals(user.getUserName(), optional.get().getUserName());
    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTheUser() {
        User user1 = new User("user24", "user@gmail.com","123");
        userRepository.save(user1);
        userRepository.deleteById(user1.getUser_id());
        Optional optional = userRepository.findById(user1.getUser_id());
        assertEquals(Optional.empty(), optional);
    }
}
