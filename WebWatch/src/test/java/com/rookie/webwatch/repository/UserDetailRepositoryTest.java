package com.rookie.webwatch.repository;

import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.entity.UserDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailRepositoryTest {
    @Autowired
    UserDetailRepository detailRepository;

    @Test
    public void createUserDetail(){
        UserDetail detail = new UserDetail();
        User user1 = new User(48,"thanghuynh", "thanghuynh@gmail.com","123456");
        detail.setUdetail_id(20L);
        detail.setUdetailAddress("test 20");
        detail.setUdetailPhone(123456789);
        detail.setUser(user1);
        //user.setRoles("user");

        Assert.assertNotNull(detailRepository.save(detail));
    }
}
