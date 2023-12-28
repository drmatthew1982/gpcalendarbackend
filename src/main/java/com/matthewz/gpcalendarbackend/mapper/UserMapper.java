package com.matthewz.gpcalendarbackend.mapper;
import com.matthewz.gpcalendarbackend.users.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "userMapper")
public interface UserMapper {
    List<User> selectUserList();
    List<User> findUser(@Param("username")String username, @Param("password")String password);
    List<String> getSeckey(@Param("username")String username);
    void updateSeckey(@Param("userid")String userid, @Param("seckey")String seckey);
}
