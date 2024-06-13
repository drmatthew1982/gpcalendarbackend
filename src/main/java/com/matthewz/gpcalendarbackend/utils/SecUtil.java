package com.matthewz.gpcalendarbackend.utils;

//import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecUtil {
    public static String decrypt(String algorithm,String keyAlgorithm, String cipherText, String keyText, String ivText) throws NoSuchPaddingException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException {
        byte[] encrypted1= Base64.getDecoder().decode(cipherText);
        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKeySpec secKey = new SecretKeySpec(keyText.getBytes(), keyAlgorithm);
        IvParameterSpec ivspec = new IvParameterSpec(ivText.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secKey,ivspec);
        byte[] plainText = cipher.doFinal(encrypted1);
        return new String(plainText);
    }

    public static String fulfillZeroTo16(String string){
        for (int i = string.length();i < 16; i++) {
            string = string+"0";
        }
        System.out.println(string);
        return string;
    }
    //https://stackoverflow.com/questions/66235649/why-does-c-sharp-and-java-application-calculate-different-md5-values-for-the-sam
    //https://stackoverflow.com/questions/9655181/java-convert-a-byte-array-to-a-hex-string/13006907#13006907
    public static String md5(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(string.getBytes());
        byte[] digest = md.digest();
        String md5String = DatatypeConverter
                .printHexBinary(digest);
//        StringBuilder sb = new StringBuilder(digest.length * 2);
//        for(byte b: digest)
//            sb.append(String.format("%02x", b));
//        return sb.toString();
          return md5String;
    }
    public static void main(String args[]) throws NoSuchAlgorithmException {
        System.out.println(md5(md5("retsettester").toLowerCase()+"123456"));
        System.out.println(md5("retsettester").toLowerCase());
        System.out.println(md5("020f53912edb7cefaa36aaa6f374542e8xFO3tknGE"));
    }
}
