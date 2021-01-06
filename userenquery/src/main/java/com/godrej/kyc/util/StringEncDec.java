package com.godrej.kyc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringEncDec {
	    
	private static final String encryptionKey           = "GodrejPPropertie";//GodrejPPropertie
    private static final String characterEncoding       = "UTF-8";
    private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";
    
    
    /**
     * Method for Encrypt Plain String Data
     * @param plainText
     * @return encryptedText
     */
    public static String encrypt(String plainText) {
        String encryptedText = "";
        try {
            Cipher cipher   = Cipher.getInstance(cipherTransformation);
            byte[] key      = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);
            encryptedText = URLEncoder.encode(encryptedText, "UTF-8");
        } catch (Exception E) {
             System.err.println("Encrypt Exception : "+E.getMessage());
        }
        return encryptedText;
    }

    /**
     * Method For Get encryptedText and Decrypted provided String
     * @param encryptedText
     * @return decryptedText
     */
    public static String decrypt(String encryptedText) {
        String decryptedText = "";
        try {
        	String decodedStr = URLDecoder.decode(encryptedText, "UTF-8");
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(decodedStr.getBytes("UTF8"));
            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception E) {
            System.err.println("decrypt Exception : "+E.getMessage());
        }
        return decryptedText;
    }
    
    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        //System.out.println("Enter String : ");
        String plainString = "9869971727";
        
        String encyptStr   = encrypt(plainString);
        String decryptStr  = decrypt(encyptStr);
        
       // System.out.println("Plain   String  : "+plainString);
       // System.out.println("Encrypt String  : "+encyptStr);
       // System.out.println("Decrypt String  : "+decryptStr);
        try {
			String encodedStr = URLEncoder.encode(encrypt(plainString), "UTF-8");
			System.out.println("Encripted String  : "+encodedStr.toString());
			//decrypt(encodedStr)
			String decodedStr = URLDecoder.decode(encodedStr, "UTF-8");
			System.out.println("Decripted String  : "+decodedStr);
			String decodedStr123 = decrypt(decodedStr);
			System.out.println("Actual String  : "+decodedStr123);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }   
    
    

}
