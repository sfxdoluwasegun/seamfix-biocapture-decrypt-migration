package com.sf.crypt.tool;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.sf.crypt.AppKey;
import com.sf.crypt.Base64Coder;
import org.apache.commons.lang.CharUtils;

public class CryptTool {

    private static CryptTool tool;
    private Cipher cipher;

    private CryptTool() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new AppKey());
    }

    public static CryptTool getInstance() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        if (tool == null) {
            synchronized (CryptTool.class) {
                tool = new CryptTool();
            }
        }
        return tool;
    }

    public String decrypt(String encryptedText) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        try {
            if(encryptedText == null){
                return null;
            }
            byte[] cp = Base64Coder.decode(encryptedText);
            byte[] plain = cipher.doFinal(cp);
            String plainText = new String(plain, "UTF8");

            if (isEncrypted(plainText)) {
                return decrypt(plainText);
            }

            return plainText;
        } catch (Throwable e) {
            System.out.println("returning enc data = -" + encryptedText + "-");
            return encryptedText;
        }
    }

    private boolean isEncrypted(String text) {
        try {
            
            if(!isAscii(text)){
                return true;
            }
            
            byte[] cp = Base64Coder.decode(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private static boolean isAscii(String plainText) {
        for (int x = 0; x < plainText.length(); x++) {

            char ch = plainText.charAt(x);

            if (!CharUtils.isAsciiAlphanumeric(ch)) {
                return false;
            }
        }
        return true;
    }
}
