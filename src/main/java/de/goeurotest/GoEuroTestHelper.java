package de.goeurotest;

import de.goeurotest.json.IJsonArrayFetcher;
import de.goeurotest.json.impl.JsonArrayFetcher;

/**
 * @author Ulas Yilmaz <ulasyilmaz@web.de>
 * 
 *         This class fetches a JSON array from an URL, finds certain fields for
 *         each object in the array and saves them into a CVS file.
 * 
 */
public class GoEuroTestHelper {

	private String baseUrl = "http://api.goeuro.com/api/v2/position/suggest/en";
	private String encoding = "UTF-8";
	private String outputDirectory = ".";
	private boolean headerOn = false;

	private IJsonArrayFetcher jsonArrayFetcher = new JsonArrayFetcher();

	/**
	 * Fetches a JSON array from a URL, finds fields <_id>, <name>, <type>,
	 * <latitude> and <longitude> for each object in the array and saves them
	 * into a CVS file. By default, the URL is
	 * http://api.goeuro.com/api/v2/position/suggest/en/<urlSuffix>.
	 * 
	 * See also setBaseUrl, setEncoding and setOutputDirectory.
	 * 
	 * @param urlSuffix
	 *            URL suffix to be added to baseUrl
	 */
	public boolean fetchAndSave(String urlSuffix) {

		String url = baseUrl + "/" + urlSuffix;
		String filename = outputDirectory + "/" + urlSuffix + ".csv";
		return jsonArrayFetcher.fetchAndSave(url, encoding, headerOn, filename);
	}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl
	 *            the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding
	 *            the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @return the outputDirectory
	 */
	public String getOutputDirectory() {
		return outputDirectory;
	}

	/**
	 * @param outputDirectory
	 *            the outputDirectory to set
	 */
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	/**
	 * @return the headerOn
	 */
	public boolean isHeaderOn() {
		return headerOn;
	}

	/**
	 * @param headerOn
	 *            the headerOn to set
	 */
	public void setHeaderOn(boolean headerOn) {
		this.headerOn = headerOn;
	}
}