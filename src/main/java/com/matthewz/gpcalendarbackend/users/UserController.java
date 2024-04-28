package com.matthewz.gpcalendarbackend.users;
import com.matthewz.gpcalendarbackend.mapper.UserMapper;
import com.matthewz.gpcalendarbackend.utils.SecUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.DatatypeConverter;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
//    @GetMapping("/hello")
//    public List<User> hello() {
//        List<User> users = userMappper.selectUserList();
//        return users;
//    }
    @PostMapping("/logincheck")
    public ResponseEntity<Object> logincheck(String username, String password, HttpServletResponse response) throws NoSuchAlgorithmException {
        List<User> users = userMappper.findUser(username);
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(users.size()>0) {
            User user = users.get(0);
            if(password.toLowerCase().equals(SecUtil.md5(user.getPassword()+user.getSeckey()).toLowerCase())) {
                System.out.println("pass");
                //this.updatesecky(user.getId());
                return new ResponseEntity<Object>(users, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Object>(new ArrayList<User>(), HttpStatus.OK);
    }

    @PostMapping("/updatepassword")
    public ResponseEntity<Object> updatepassword(User user, HttpServletResponse response) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        if(user.getUsername().equals("tester")){
            new ResponseEntity<Object>("tester cannot update password",HttpStatus.OK);
        }
        //AES/CBC/PKCS5Padding
        //AES/CBC/NoPadding
        //AES/CBC/ZeroPadding
        String decodedPassword = SecUtil.decrypt("AES/CBC/NoPadding","AES",user.getPassword(),SecUtil.fulfillZeroTo16(user.getUsername().substring(0,3)),SecUtil.fulfillZeroTo16(user.getUsername().substring(user.getUsername().length()-3)) );

        userMappper.updatePassword(user.getId(),decodedPassword.substring(0,decodedPassword.length()-16));
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
