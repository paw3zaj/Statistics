package pl.pzdev2.virtua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.pzdev2.virtua.interfaces.VirtuaRepository;
import pl.pzdev2.virtua.interfaces.VirtuaUpdateHandler;

public class VirtuaUpdateService implements VirtuaUpdateHandler {

	private VirtuaRepository virtuaRepository;
	private List<Virtua> dataFromApi;
	private List<Virtua> dataFromDb;
	
	private static final Logger log = LoggerFactory.getLogger(VirtuaUpdateService.class);
	
	public VirtuaUpdateService(VirtuaRepository virtuaRepository, List<Virtua> dataFromApi, List<Virtua> dataFromDb) {
		this.virtuaRepository = virtuaRepository;
		this.dataFromApi = dataFromApi;
		this.dataFromDb = dataFromDb;
	}

	@Override
	public List<Virtua> convertDataFromApiToVirtua(Scanner input) {
		
		List<Virtua> fromApi = new ArrayList<>();
		
		 while (input.hasNextLine()) {
	            String line = input.nextLine();
	            String[] parts = line.split("\t");
	            try {
	                fromApi.add(Virtua.builder()
	                		.readingRoom(parts[0])
	                		.idVirtua(Long.parseLong(parts[1]))
	                		.signature(parts[2])
	                		.barcode(parts[3])
	                		.author(parts[4])
	                		.title(parts[5])
	                		.status(Status.IN)
	                		.build());
	            } catch (Exception e) {
	                log.info("Scanner wyrzucił‚ błąd idVirtua: {}", Long.parseLong(parts[1]));
	                continue;
	            }
	        }
	        
		 return fromApi;
	}

	@Override
	public List<List<Virtua>> updateVirtuaDatabase(List<Virtua> dataFromApi, List<Virtua> dataFromDb) {
		
//		remove the same elements from both Virtua lists
		removeTheSameElements(dataFromApi, dataFromDb);
		
//		separate the id from both Virtua lists
		List<Long> dbIdList = idSeparate(dataFromDb);
		List<Long> apiIdList = idSeparate(dataFromApi);
		
//		books updating in the reading room
		List<Virtua> updateDbRows = updateVirtua(dataFromApi, dbIdList);
		
//		books entering to the reading room
		List<Virtua> statusIn = changeStatus(dataFromApi, dbIdList);
		statusIn.forEach(v -> {
			v.setStatus(Status.IN);
		});
		
//		books leaving from the reading room
		List<Virtua> statusOut = changeStatus(dataFromDb, apiIdList);
		statusOut.forEach(v -> {
			v.setStatus(Status.OUT);
		});
		
		return Arrays.asList(updateDbRows, statusIn, statusOut);
		
	}
	
	 void removeTheSameElements(List<Virtua> dataFromApi, List<Virtua> dataFromDb) {

		List<Virtua> temp = new ArrayList<>(dataFromApi);
		
		dataFromApi.removeAll(dataFromDb);
		dataFromDb.removeAll(temp);
	}

	 List<Long> idSeparate(List<Virtua> virtuaList) {
		 return virtuaList.stream()
				 .map(v -> v.getIdVirtua())
				 .collect(Collectors.toList());
	 }
	 
	 List<Virtua> updateVirtua(List<Virtua>dataFromApi, List<Long>dbIdList) {
		 return dataFromApi.stream()
				 .filter(v -> dbIdList.contains(v.getIdVirtua()))
				 .collect(Collectors.toList());
	 }
	 
	 List<Virtua> changeStatus(List<Virtua> virtuaList, List<Long> idList) {
		 return virtuaList.stream()
				 .filter(v -> !idList.contains(v.getIdVirtua()))
				 .collect(Collectors.toList());
	 }
}
