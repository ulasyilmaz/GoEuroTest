package de.goeurotest.json.impl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import de.goeurotest.json.IJsonArrayFetcher;

/**
 * @author Ulas Yilmaz <ulasyilmaz@web.de>
 * 
 *         Default implementation of {@link IJsonArrayFetcher}.
 *
 */
public class JsonArrayFetcher implements IJsonArrayFetcher {

	private final static Logger logger = Logger.getLogger(JsonArrayFetcher.class.getName());

	static {
		logger.setLevel(Level.ALL);
		//logger.setLevel(Level.OFF);
	}

	@Override
	public boolean fetchAndSave(String url, String encoding, boolean headerOn, String filename) {

		logger.info("Fetching from URL: " + url);
		String text = fetchTextFromUrl(url, encoding);
		if (text == null) {
			logger.severe("Fetching from URL failed!");
			return false;
		}

		// logger.info("Converting text to JSONArray: " + text);
		logger.info("Converting text to JSONArray: " + "<text>");
		JSONArray jsonArray = convertTextToJsonArray(text);
		if (jsonArray == null) {
			logger.severe("Converting text to JSONArray failed!");
			return false;
		}

		logger.info("Writing to file: " + filename);
		boolean flag = writeJsonArrayToFile(jsonArray, headerOn, filename, encoding);
		if (!flag) {
			logger.severe("Writing to file failed!");
			return false;
		}

		return true;
	}

	/**
	 * Fetches text from URL
	 * 
	 * @param url
	 *            URL to be queried
	 * @param encoding
	 *            Encoding of URL
	 * @return Fetched text from URL
	 */
	String fetchTextFromUrl(String url, String encoding) {
		try {
			URI uri = new URI(url);
			String text = IOUtils.toString(uri, encoding);
			return text;
		} catch (URISyntaxException e) {
			logger.severe("Invalid URL, check syntax: " + url);
			return null;
		} catch (Exception e) {
			logger.severe("Invalid URL, check connection: " + url);
			return null;
		}
	}

	/**
	 * Converts text to JSONArray
	 * 
	 * @param text
	 *            Text to be converted
	 * @return JSONArray extracted from text
	 */
	JSONArray convertTextToJsonArray(String text) {
		try {
			return new JSONArray(text);
		} catch (Exception e) {
			logger.severe("Fetched data cannot be converted to JSONArray");
			return null;
		}
	}

	/**
	 * Writes fields <_id>, <name>, <type>, <latitude> and <longitude> for each
	 * object in JSONArray to a file in CSV format
	 * 
	 * @param jsonArray
	 *            JSONArray to be saved
	 * @param headerOn
	 *            Flag to show whether or not to write header to CSV file
	 * @param filename
	 *            Name of file in which the results are written
	 * @param encoding
	 *            Encoding of text
	 * @return Success of write operation
	 */
	boolean writeJsonArrayToFile(JSONArray jsonArray, boolean headerOn, String filename, String encoding) {

		String results = "";

		// Collect results
		for (int n = 0; n < jsonArray.length(); n++) {
			JSONObject jsonObject = jsonArray.getJSONObject(n);
			if (jsonObject == null) {
				logger.warning("Object Nr. " + n + " is null, will therefore be skipped.");
			} else {
				// name and geo_position are mandatory fields
				// see https://github.com/goeuro/dev-test
				if (!jsonObject.has("name") || !jsonObject.has("geo_position")) {
					logger.warning("Object Nr. " + n + " has missing fields, will therefore be skipped.");
				} else {
					if (jsonObject.has("_id")) {
						results += jsonObject.get("_id");
					}
					results += ",";
					results += jsonObject.get("name");
					results += ",";
					if (jsonObject.has("type")) {
						results += jsonObject.get("type");
					}
					results += ",";
					JSONObject geo_position = (JSONObject) jsonObject.get("geo_position");
					if (geo_position.has("latitude")) {
						results += geo_position.get("latitude");
					}
					results += ",";
					if (geo_position.has("longitude")) {
						results += geo_position.get("longitude");
					}
					results += String.format("%n");
				}
			}
		}

		// Header
		if (headerOn) {
			results = "_id,name,type,latitude,longitude" + String.format("%n") + results;
		}

		// Save results
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), encoding));
			writer.write(results);
			return true;
		} catch (IOException e) {
			logger.severe("Cannot write into file: " + filename);
			return false;
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/* ignore */
			}
		}
	}

}
