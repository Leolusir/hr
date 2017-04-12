package com.devils.hr;

import com.devils.hr.querys.DCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HreduApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("---------start test----------");
		System.out.println("");

		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			Criteria cr = new Criteria();
			cr.andOperator(Criteria.where("test").is("test"),
					Criteria.where("test1").is("test1"),
					Criteria.where("test2").is("test2"));
		}
		long t2 = System.currentTimeMillis();

		System.out.println("new operation spends : " + (t2 - t1) + "ms");

		long t3 = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			Criteria cr = DCriteria.getOneCriteria();
			cr.andOperator(Criteria.where("test").is("test"),
					Criteria.where("test1").is("test1"),
					Criteria.where("test2").is("test2"));
		}
		long t4 = System.currentTimeMillis();

		System.out.println("clone operation spend : " + (t4 - t3) + "ms");
	}

}
