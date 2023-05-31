package com.matthewz.gpcalendarbackend.users;
import com.matthewz.gpcalendarbackend.mapper.UserMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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
    @RequestMapping("/logincheck")
    public ResponseEntity<Object> logincheck(String username, String password, HttpServletResponse response) {
        List<User> users = userMappper.findUser(username,password);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(users, HttpStatus.OK);
    }
}
