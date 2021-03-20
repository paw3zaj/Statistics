package pl.pzdev2.virtua;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.pzdev2.utility.DateTimeUtility;
import pl.pzdev2.virtua.interfaces.DataFetch;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

@Service
public class DataFetchService implements DataFetch {
	
	private final VirtuaRepository virtuaRepository;
	
	private static final Logger LOG = LoggerFactory.getLogger(DataFetchService.class);

	public DataFetchService(VirtuaRepository virtuaRepository) {
	this.virtuaRepository = virtuaRepository;
	}

	@Override
	public Scanner getDataFromApi(String path) {
		
//		Pass the desired URL as an object.
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			LOG.info("Error!!! Throws an error when passing url to object {}", DateTimeUtility.getDateTimeAsString());
		}

//		We will be able to harness the properties of the HttpURLConnection class to validate features.
		HttpURLConnection conn = null;
		try {
			if (url != null) {
				conn = (HttpURLConnection) url.openConnection();
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOG.info("Error!!! A connection can't be establish {}", DateTimeUtility.getDateTimeAsString());
		}

//		Set the request type.
		try {
			if (conn != null) {
				conn.setRequestMethod("GET");
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
			LOG.info("Error!!! Can't set the request type {}", DateTimeUtility.getDateTimeAsString());
		}

//		Open a connection stream to the corresponding API.
		try {
			if (conn != null) {
				conn.connect();
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOG.info("Error!!! A connection can't be opened {}", DateTimeUtility.getDateTimeAsString());
		}

//		Get the corresponding response code.
		int responsecode = 0;
		try {
			if (conn != null) {
				responsecode = conn.getResponseCode();
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOG.info("Error!!! Can't Get the corresponding response code. {}", DateTimeUtility.getDateTimeAsString());
		}

//		Perform a check.
        if (responsecode != 200) {
        	LOG.info("Błąd połączenia z API Virtua!!!	{}\nHttpResponseCode: " + responsecode, DateTimeUtility.getDateTimeAsString());
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        }


		Scanner input = null;
		try {
			input = new Scanner(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
			LOG.info("Error!!! Data can't be scanned from api {}", DateTimeUtility.getDateTimeAsString());
		}

		return input;
	}

	@Override
	public List<Virtua> getDataFromDatabase() {
		return virtuaRepository.findAll();
	}
}
