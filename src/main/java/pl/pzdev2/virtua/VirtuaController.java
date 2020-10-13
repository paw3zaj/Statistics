package pl.pzdev2.virtua;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.pzdev2.virtua.interfaces.DataFetch;
import pl.pzdev2.virtua.interfaces.VirtuaUpdateHandler;

@RestController
public class VirtuaController {
	
	private DataFetch dataFetch;
	private VirtuaUpdateHandler virtuaUpdateHandler;
	
	private static final String URL = "http://153.19.58.250:8000/cgi-bin/27/gumed-czytczas.cgi";
	
	public VirtuaController(DataFetch dataFetch, VirtuaUpdateHandler virtuaUpdateHandler) {
		this.dataFetch = dataFetch;
		this.virtuaUpdateHandler = virtuaUpdateHandler;
	}
	
	@Profile("prod")
    @Scheduled(cron = "0 0 23 * * *")    //every day at 23:00 pm
	void booksResources() throws IOException {
//		var input = dataFetch.getDataFromApi(URL);
//		List<Virtua> dataFromApi = virtuaUpdateHandler.convertDataFromApiToVirtua(input);
//		List<Virtua> dataFromDb = dataFetch.getDataFromDatabase();
//		virtuaUpdateHandler.updateVirtuaDatabase(dataFromApi, dataFromDb);
	}
	
	@GetMapping("/fetch")
	@Profile("dev")
	List<Virtua> booksResourcesDev() throws IOException {
		var input = dataFetch.getDataFromApi(URL);
		List<Virtua> dataFromApi = virtuaUpdateHandler.convertDataFromApiToVirtua(input);
		List<Virtua> dataFromDb = dataFetch.getDataFromDatabase();
		List<Virtua> toUpdate = virtuaUpdateHandler.updateVirtuaDatabase(dataFromApi, dataFromDb);
		return virtuaUpdateHandler.saveChanges(toUpdate);
	}

}
