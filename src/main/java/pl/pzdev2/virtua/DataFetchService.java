package pl.pzdev2.virtua;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.pzdev2.utility.FormatDateTime;
import pl.pzdev2.virtua.interfaces.DataFetch;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

@Service
public class DataFetchService implements DataFetch {
	
	private VirtuaRepository virtuaRepository;
	
	private static final Logger LOG = LoggerFactory.getLogger(VirtuaUpdateService.class);
	
	public DataFetchService(VirtuaRepository virtuaRepository) {
	this.virtuaRepository = virtuaRepository;
	}

	@Override
	public Scanner getDataFromApi(String path) throws IOException {
		
//		Pass the desired URL as an object.
        URL url = new URL(path);

//		We will be able to harness the properties of the HttpURLConnection class to validate features.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

//		Set the request type.
        conn.setRequestMethod("GET");

//		Open a connection stream to the corresponding API.
        conn.connect();

//		Get the corresponding response code.
        int responsecode = conn.getResponseCode();

//		Perform a check.
        if (responsecode != 200) {
        	LOG.info("Błąd połączenia z API Virtua!!!	{}\nHttpResponseCode: " + responsecode, FormatDateTime.getDateTime());
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        }
        
        return new Scanner(url.openStream());
	}

	@Override
	public List<Virtua> getDataFromDatabase() {
		return virtuaRepository.findAll();
	}
}
