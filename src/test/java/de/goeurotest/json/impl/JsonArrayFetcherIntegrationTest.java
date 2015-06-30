package de.goeurotest.json.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Ulas Yilmaz <ulasyilmaz@web.de>
 *
 */
public class JsonArrayFetcherIntegrationTest {

	private JsonArrayFetcher jsonArrayFetcher = new JsonArrayFetcher();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Ignore("Requires running server")
	public void testFetchAndSave() {

		// given
		String url = "http://api.goeuro.com/api/v2/position/suggest/en/Berlin";
		String encoding = "UTF-8";
		boolean headerOn = false;
		String filename = "./Berlin.csv";

		// when
		boolean flag = jsonArrayFetcher.fetchAndSave(url, encoding, headerOn, filename);

		// then
		assertNotNull(flag);

		// remove created test file
		try {
			FileUtils.forceDelete(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore("Requires running server")
	public void testFetchTextFromUrl() {

		// given
		String url = "http://api.goeuro.com/api/v2/position/suggest/en/Berlin";
		String encoding = "UTF-8";

		// when
		String text = jsonArrayFetcher.fetchTextFromUrl(url, encoding);

		// then
		assertNotNull(text);
	}

	@Test
	@Ignore("Requires Internet connection")
	public void testFetchTextFromGoogle() {

		// given
		String url = "http://www.google.com";
		String encoding = "UTF-8";

		// when
		String text = jsonArrayFetcher.fetchTextFromUrl(url, encoding);

		// then
		assertNotNull(text);
	}

	@Test
	public void testConvertTextToJsonArray() {

		// given
		String text = "[{\"_id\":377078,\"key\":null,\"name\":\"Potsdam\",\"fullName\":\"Potsdam, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.39886,\"longitude\":13.06566},\"locationId\":9254,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":410978,\"key\":null,\"name\":\"Potsdam\",\"fullName\":\"Potsdam, USA\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"USA\",\"geo_position\":{\"latitude\":44.66978,\"longitude\":-74.98131},\"locationId\":43214,\"inEurope\":false,\"countryCode\":\"US\",\"coreCountry\":false,\"distance\":null}]";

		// when
		JSONArray jsonArray = jsonArrayFetcher.convertTextToJsonArray(text);

		// then
		assertEquals(jsonArray.length(), 2);
	}

	@Test
	public void testWriteJsonArrayToFile() {

		// given
		String name = "TEST_NAME";
		JSONObject geo_position = new JSONObject();
		// and
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("geo_position", geo_position);
		// and
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(jsonObject);
		jsonArray.put(jsonObject);
		jsonArray.put(jsonObject);
		jsonArray.put(jsonObject);
		assertEquals(jsonArray.length(), 4);
		// and
		boolean headerOn = false;
		String filename = "./Berlin.csv";
		String encoding = "UTF-8";

		// when
		boolean flag = jsonArrayFetcher.writeJsonArrayToFile(jsonArray, headerOn, filename, encoding);

		// then
		assertTrue(flag);

		// remove created test file
		try {
			FileUtils.forceDelete(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
