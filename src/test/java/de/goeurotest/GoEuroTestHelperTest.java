package de.goeurotest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.goeurotest.json.impl.JsonArrayFetcher;

/**
 * @author Ulas Yilmaz <ulasyilmaz@web.de>
 *
 */
public class GoEuroTestHelperTest {

	@InjectMocks
	private GoEuroTestHelper GoEuroTestHelper;
	
	@Mock
	private JsonArrayFetcher jsonArrayFetcher; 

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFetchAndSave() {

		// given
		String url = "PLACE_HOLDER";
		String encoding = "PLACE_HOLDER";
		boolean headerOn = false;
		String filename = "PLACE_HOLDER";

		// by mocking
		doReturn(true).when(jsonArrayFetcher).fetchAndSave(Matchers.anyString(), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString());
		
		// then
		boolean result = jsonArrayFetcher.fetchAndSave(url, encoding, headerOn, filename);
		assertTrue(result);
	}

}
