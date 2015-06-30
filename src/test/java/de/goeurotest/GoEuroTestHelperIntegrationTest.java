package de.goeurotest;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Ulas Yilmaz <ulasyilmaz@web.de>
 *
 */
public class GoEuroTestHelperIntegrationTest {

	private GoEuroTestHelper goEuroTestHelper = new GoEuroTestHelper();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Ignore("Requires running server")
	public void testFetchAndSave() {

		// given
		String urlSuffix = "Berlin";
		goEuroTestHelper.setBaseUrl("http://api.goeuro.com/api/v2/position/suggest/en");
		goEuroTestHelper.setEncoding("UTF-8");
		goEuroTestHelper.setOutputDirectory(".");
		goEuroTestHelper.setHeaderOn(false);

		// when
		boolean flag = goEuroTestHelper.fetchAndSave(urlSuffix);
		
		// then
		assertNotNull(flag);

		// remove created test file
		String filename = goEuroTestHelper.getOutputDirectory() + "/" + urlSuffix + ".csv";
		try {
			FileUtils.forceDelete(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
