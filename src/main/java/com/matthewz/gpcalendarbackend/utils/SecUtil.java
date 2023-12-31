package com.matthewz.gpcalendarbackend.utils;

import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
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
    public static String md5(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(string.getBytes());
        byte[] digest = md.digest();
        String md5String = DatatypeConverter
                .printHexBinary(digest);
       return md5String;
    }
}
