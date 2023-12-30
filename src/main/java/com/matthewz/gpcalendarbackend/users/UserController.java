package com.matthewz.gpcalendarbackend.users;
import com.matthewz.gpcalendarbackend.mapper.UserMapper;
import com.matthewz.gpcalendarbackend.utils.SecUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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
    @PostMapping("/logincheck")
    public ResponseEntity<Object> logincheck(String username, String password, HttpServletResponse response) {
        List<User> users = userMappper.findUser(username,password);
        if(users.size()>0) {
            this.updatesecky(users.get(0).getId());
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(users, HttpStatus.OK);
    }

    @PostMapping("/updatepassword")
    public ResponseEntity<Object> updatepassword(User user, HttpServletResponse response) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        System.out.println(user.getPassword());
        //AES/CBC/PKCS5Padding
        //AES/CBC/NoPadding
        //AES/CBC/ZeroPadding
        String decodedPassword = SecUtil.decrypt("AES/CBC/NoPadding","AES",user.getPassword(),"1111222233334444","1111222233334444" );
        System.out.println(decodedPassword);
        System.out.println(decodedPassword.substring(0,decodedPassword.length()-6));
        //userMappper.updatePassword(id,password);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    @PostMapping("/getSecKey")
    public ResponseEntity<Object> getSeckey(User user, HttpServletResponse response) {
        List<String> seckeys = userMappper.getSeckey(user.getUsername());
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(seckeys, HttpStatus.OK);
    }

    public void updatesecky(String id) {
        String seckey = RandomStringUtils.randomAlphanumeric(10);
        userMappper.updateSeckey(id,seckey);
    }
}
