package pl.pzdev2.virtua.interfaces;

import java.util.List;
import java.util.Scanner;

import pl.pzdev2.virtua.Virtua;

public interface VirtuaHandler {

	List<Virtua> convertDataFromApiToVirtua(Scanner input);
 	void updateVirtuaDatabase(List<Virtua> dataFromApi, List<Virtua> dataFromDb, List<Long> dbIdList);
}
