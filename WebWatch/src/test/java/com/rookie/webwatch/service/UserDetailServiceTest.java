package com.rookie.webwatch.service;

import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.entity.UserDetail;
import com.rookie.webwatch.repository.UserDetailRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserDetailServiceTest {
    @Autowired
    UserDetailService detailService;

    @MockBean
    UserDetailRepository detailRepository;

    private User user = new User(48,"thanghuynh", "thanghuynh@gmail.com","123456");
    private UserDetail detail = new UserDetail(20L, 123456789,  "test 20", user);

    @Test
    public void addUserDetail(){
        when(detailRepository.save(any())).thenReturn(detail);

        Assert.assertEquals("test 20", detail.getUdetailAddress());
    }
}
