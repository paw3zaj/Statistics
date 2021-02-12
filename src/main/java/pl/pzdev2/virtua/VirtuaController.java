package pl.pzdev2.virtua;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.pzdev2.utility.FormatDateTime;
import pl.pzdev2.virtua.interfaces.DataFetch;
import pl.pzdev2.virtua.interfaces.VirtuaUpdateHandler;

@Component
public class VirtuaController {
	
	private final DataFetch dataFetch;
	private final VirtuaUpdateHandler virtuaUpdateHandler;

	private static final Logger LOG = LoggerFactory.getLogger(VirtuaController.class);

	@Value("#{systemProperties['virtua.url']}")
	private static String URL;
	
	public VirtuaController(DataFetch dataFetch, VirtuaUpdateHandler virtuaUpdateHandler) {
		this.dataFetch = dataFetch;
		this.virtuaUpdateHandler = virtuaUpdateHandler;
	}
	
    @Scheduled(cron = "0 0 23 * * MON-FRI")    //weekdays at 23:00 pm
	void booksResources() {
		LOG.info("Start downloading data from API. {}", FormatDateTime.getDateTime());

		Scanner input = null;
		try {
			input = dataFetch.getDataFromApi(URL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Virtua> dataFromApi = virtuaUpdateHandler.convertDataFromApiToVirtua(input);
		List<Virtua> dataFromDb = dataFetch.getDataFromDatabase();
		List<Virtua> toUpdate = virtuaUpdateHandler.updateVirtuaDatabase(dataFromApi, dataFromDb);
		virtuaUpdateHandler.saveChanges(toUpdate);

		LOG.info("End of downloading data from API. {}", FormatDateTime.getDateTime());
	}

}
