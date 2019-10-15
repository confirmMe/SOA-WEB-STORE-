package test;

import org.springframework.util.DigestUtils;

public class TestMD5 {

	
	public static void main(String[] args) {
		String a="hello";
		byte[] result=DigestUtils.md5Digest(a.getBytes());
	 	System.out.println(result);
        
	}
}
