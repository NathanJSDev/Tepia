package com.nd.tepia;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TepiaApplicationTests {

	@Test
	void contextLoads() {
		String[] data = new String[]{"key1","value1","key2","value2"};
		boolean isInteger = (((data.length / 2) % 2 ) == 0) ? true : false;
		if (isInteger)
			for (int i = 0; i < data.length; i+=2) {
				String key = data[i];
				String value = data[i+1];

				System.out.println(key + " : " + value);
			}
		else throw new RuntimeException("'Data' Array is corrupted.");
	}

}
