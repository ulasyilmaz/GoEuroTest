package de.goeurotest;

/**
 * @author Ulas Yilmaz <ulasyilmaz@web.de>
 * 
 *         This test project fetches a JSON array from an URL
 *         http://api.goeuro.com/api/v2/position/suggest/en/<CITY_NAME>, finds
 *         fields <_id>, <name>, <type>, <latitude> and <longitude> for each
 *         object in the array and saves them into a CVS file.
 * 
 *         USAGE: java -jar GoEuroTest.jar <CITY_NAME>
 * 
 *         Example: java -jar GoEuroTest.jar "Berlin"
 * 
 *         Fetches from URL
 *         http://api.goeuro.com/api/v2/position/suggest/en/Berlin and creates
 *         file Berlin.csv
 * 
 */
public class GoEuroTest {

	/**
	 * @param args
	 *            args[0] is the city name to be looked up
	 */
	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("USAGE: java -jar GoEuroTest.jar <CITY_NAME>");
		} else {
			GoEuroTestHelper goEuroTestHelper = new GoEuroTestHelper();
			// goEuroTestHelper.setBaseUrl("http://api.goeuro.com/api/v2/position/suggest/en");
			// goEuroTestHelper.setEncoding("UTF-8");
			// goEuroTestHelper.setOutputDirectory(".");
			// goEuroTestHelper.setHeaderOn(false);
			goEuroTestHelper.fetchAndSave(args[0]);

		}
	}
}
