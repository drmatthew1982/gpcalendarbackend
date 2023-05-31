package com.matthewz.gpcalendarbackend.mapper;
import com.matthewz.gpcalendarbackend.users.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "userMapper")
public interface UserMapper {
    List<User> selectUserList();
    List<User> findUser(@Param("username")String username, @Param("password")String password);
}
