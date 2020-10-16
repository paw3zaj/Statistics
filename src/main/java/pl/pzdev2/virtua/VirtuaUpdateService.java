package pl.pzdev2.virtua;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.pzdev2.utility.FormatDateTime;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;
import pl.pzdev2.virtua.interfaces.VirtuaUpdateHandler;

@Service
public class VirtuaUpdateService implements VirtuaUpdateHandler {

	private VirtuaRepository virtuaRepository;
	
	private static final Logger LOG = LoggerFactory.getLogger(VirtuaUpdateService.class);
	
	public VirtuaUpdateService(VirtuaRepository virtuaRepository) {
		this.virtuaRepository = virtuaRepository;
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
	                LOG.info("Scanner wyrzucił‚ błąd idVirtua: {}	{}", Long.parseLong(parts[1]), FormatDateTime.getDateTime());
	                continue;
	            }
	        }
	        
		 return fromApi;
	}

	@Override
	public List<Virtua> updateVirtuaDatabase(List<Virtua> dataFromApi, List<Virtua> dataFromDb) {
		
//		remove the same elements from both Virtua lists
		removeTheSameElements(dataFromApi, dataFromDb);
		
//		separate the id from both Virtua lists
		List<Long> dbIdList = idSeparate(dataFromDb);
		List<Long> apiIdList = idSeparate(dataFromApi);
		
//		virtua update
		List<Virtua> toUpdate = updateVirtua(dataFromApi, dbIdList);
		
		for(Virtua virtua : toUpdate) {
			for(Virtua dbV : dataFromDb) {
				if(virtua.getIdVirtua().equals(dbV.getIdVirtua())) {
					virtua.setCreatedDate(dbV.getCreatedDate());
					break;
				}
			}
		}
		
//		books entrance to the reading room
		List<Virtua> entrance = checkVirtuaState(dataFromApi, dbIdList);
		entrance.forEach(v -> {
			v.setCreatedDate(LocalDate.now());
			toUpdate.add(v);
		});
		
//		books leave from the reading room
		List<Virtua> leave = checkVirtuaState(dataFromDb, apiIdList);
		leave.forEach(v -> {
			if(v.getStatus() == Status.IN) {
				v.setStatus(Status.OUT);
				toUpdate.add(v);
			}
			
		});
		
		return toUpdate;
	}
	
	 @Override
	public List<Virtua> saveChanges(List<Virtua> toUpdate) {
		return virtuaRepository.saveAll(toUpdate);
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
	 
	 List<Virtua> updateVirtua(List<Virtua> dataFromApi, List<Long> dbIdList) {
		 
		 return dataFromApi.stream()
				 .filter(v -> dbIdList.contains(v.getIdVirtua()))
				 .collect(Collectors.toList());
	 }
	 
	 List<Virtua> checkVirtuaState(List<Virtua> virtuaList, List<Long> idList) {
		 return virtuaList.stream()
				 .filter(v -> !idList.contains(v.getIdVirtua()))
				 .collect(Collectors.toList());
	 }
}
