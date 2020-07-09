/*
 *
 * @author: Suresh Bhaskaran
 *
 * $Id$
 *
 */
package com.mastercard.city.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceTest {

    private static final Logger log = LoggerFactory.getLogger(CityServiceTest.class);
	
	@Autowired
	private CityService cityService;

	@Test()
	@Description("Test for yes")
	public void testCanConnect() throws Exception {

		String origin = "Boston";
		String destination = "Newark";
		String answer = cityService.canConnect(origin, destination);
		assertEquals(answer, "yes");
	}

	@Test()
	@Description("Test for yes")
	public void testCanConnect2() throws Exception {

		String origin = "Boston";
		String destination = "Philadelphia";
		String answer = cityService.canConnect(origin, destination);
		assertEquals(answer, "yes");
	}

	@Test()
	@Description("Test for no")
	public void testCanConnect3() throws Exception {

		String origin = "Philadelphia";
		String destination = "Albany";
		String answer = cityService.canConnect(origin, destination);
		assertEquals(answer, "no");
	}
}
