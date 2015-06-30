package de.goeurotest.json;

import de.goeurotest.json.impl.JsonArrayFetcher;

/**
 * @author Ulas Yilmaz <ulasyilmaz@web.de>
 * 
 *         Interface of class {@link JsonArrayFetcher} to fetch a JSON array
 *         from a URL, to find certain fields for each object in the array and
 *         to save them into a file in CSV format.
 * 
 */
public interface IJsonArrayFetcher {

	/**
	 * Fetches a JSON array from a URL, finds fields <_id>, <name>, <type>,
	 * <latitude> and <longitude> for each object in the array and saves them
	 * into a file in CSV format.
	 * 
	 * @param url
	 *            URL to be queried
	 * @param encoding
	 *            Encoding of URL text
	 * @param headerOn
	 *            Flag to show whether or not to write header to CSV file
	 * @param filename
	 *            Name of file in which the results are written
	 * @return Success of fetch and save operation
	 */
	public boolean fetchAndSave(String url, String encoding, boolean headerOn, String filename);
}