package com.tomcat.hosting.utils;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class StringEncrypterTest
{

//	@Test
	public void testEncryptsUsingDes() throws Exception
	{
		String stringToEncrypt = "paul";
		String encryptionScheme = StringEncrypter.DES_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter( encryptionScheme );
		String encryptedString = encrypter.encrypt( stringToEncrypt );
		
		System.out.println(encryptedString);
		System.out.println(encrypter.decrypt(encryptedString));	
		
		
		stringToEncrypt = "f+ff==";
		encryptedString = encrypter.encrypt( stringToEncrypt );
		System.out.println(encryptedString);
		System.out.println("decrypted value" + encrypter.decrypt(""));	
	}

	@Test
	public void testDecryptsUsingDes() throws Exception
	{
		String string = "FJiBG+QGHGE=";
		String encryptionScheme = StringEncrypter.DES_ENCRYPTION_SCHEME;

		StringEncrypter encrypter = new StringEncrypter( encryptionScheme, StringEncrypter.DEFAULT_ENCRYPTION_KEY );
		String decryptedString = encrypter.decrypt( string );
System.out.println("decrypted string:" + decryptedString);
		Assert.assertEquals( "test", decryptedString );
	}
}