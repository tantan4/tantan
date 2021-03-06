package com.dream.ssh.util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SSHUtil {
	private final static Logger LOG = LogManager.getLogger(SSHUtil.class);
	
	/**
	 * @param salt
	 * @param str
	 * @return
	 */
	public static String getMD5 (String salt, String str) {
		// 生成一个MD5加密计算摘要
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			// 计算md5函数
	        md.update((salt + str).getBytes());
	        // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			return salt + str;
		}// MD5 SHA-256...
	}
	
}
