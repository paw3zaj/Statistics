package pl.pzdev2.virtua;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.pzdev2.virtua.interfaces.DataFetch;
import pl.pzdev2.virtua.interfaces.VirtuaUpdateHandler;

@Component
public class VirtuaController {
	
	private final DataFetch dataFetch;
	private final VirtuaUpdateHandler virtuaUpdateHandler;
	
	private static final String URL = "http://153.19.58.250:8000/cgi-bin/27/gumed-czytczas.cgi";
	
	public VirtuaController(DataFetch dataFetch, VirtuaUpdateHandler virtuaUpdateHandler) {
		this.dataFetch = dataFetch;
		this.virtuaUpdateHandler = virtuaUpdateHandler;
	}
	
    @Scheduled(cron = "0 0 23 * * *")    //every day at 23:00 pm
	void booksResources() throws IOException {
		Scanner input = dataFetch.getDataFromApi(URL);
		List<Virtua> dataFromApi = virtuaUpdateHandler.convertDataFromApiToVirtua(input);
		List<Virtua> dataFromDb = dataFetch.getDataFromDatabase();
		List<Virtua> toUpdate = virtuaUpdateHandler.updateVirtuaDatabase(dataFromApi, dataFromDb);
		virtuaUpdateHandler.saveChanges(toUpdate);
	}

}
