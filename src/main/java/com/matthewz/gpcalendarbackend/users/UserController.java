package com.matthewz.gpcalendarbackend.users;
import com.matthewz.gpcalendarbackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class UserController {
    @Autowired
    private UserMapper userMappper;
    /**
     * 查询所有用户信息
     */
    @GetMapping("/hello")
    public List<User> hello() {
        List<User> users = userMappper.selectUserList();
        return users;
    }
}
