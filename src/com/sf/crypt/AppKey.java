package com.sf.crypt;

import javax.crypto.SecretKey;

public class AppKey implements SecretKey{

	private String encodedKey = "ZPKU/eWJIF4=";
	
	public AppKey() {
	}
	
	public AppKey(String encodedKey){
		this.encodedKey = encodedKey;
	}
	
	private static final long serialVersionUID = 7875437463225811419L;

	
	@Override
	public String getAlgorithm() {
		return "DES";
	}

	@Override
	public String getFormat() {
		return "RAW";
	}

	@Override
	public byte[] getEncoded() {
		return Base64Coder.decode(encodedKey);
	}

}
