package com.matthewz.gpcalendarbackend.utils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
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
        byte[] plainText = cipher.doFinal(encrypted1); //<-------- GETTING ERROR IN THIS LINE
        return new String(plainText);
    }
}
