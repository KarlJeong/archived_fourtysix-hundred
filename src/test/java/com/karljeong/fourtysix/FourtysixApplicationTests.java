package com.karljeong.fourtysix;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FourtysixApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void caldiff() {
		long currentTimestamp = System.currentTimeMillis();
        Calendar todayCal = Calendar.getInstance();
        todayCal.setTimeInMillis(currentTimestamp);
        
        System.out.println(todayCal.get(Calendar.MONTH));
	}

}
