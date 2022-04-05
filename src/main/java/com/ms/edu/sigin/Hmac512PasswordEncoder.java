package com.ms.edu.sigin;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Hmac512PasswordEncoder implements PasswordEncoder {

	private static final String SSHA512_PREFIX = "{SSHA-512}";
	private static final String HMAC_SHA512 = "HmacSHA512";

	private final String salt;

	public Hmac512PasswordEncoder(String salt) {
		if (salt == null) {
			throw new IllegalArgumentException("salt cannot be null");
		}
		this.salt = salt;
	}

	public String encode(CharSequence rawPassword) {
		String result = null;

		try {
			Mac sha512Hmac = Mac.getInstance(HMAC_SHA512);
			final byte[] byteKey = Utf8.encode(salt);
			SecretKeySpec keySpec = new SecretKeySpec(byteKey, HMAC_SHA512);
			sha512Hmac.init(keySpec);
			byte[] macData = sha512Hmac.doFinal(Utf8.encode(rawPassword.toString()));

			result = Base64.getEncoder().encodeToString(macData);
			// result = bytesToHex(macData);
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (rawPassword == null || encodedPassword == null) {
			return false;
		}

		String encodedRawPass = encode(rawPassword);

		return MessageDigest.isEqual(Utf8.encode(encodedRawPass), Utf8.encode(encodedPassword));
	}
}