package pl.pzdev2.virtua.interfaces;

import java.util.List;
import java.util.Scanner;

import pl.pzdev2.virtua.Virtua;

public interface VirtuaUpdateHandler {

	List<Virtua> convertDataFromApiToVirtua(Scanner input);
 	List<List<Virtua>> updateVirtuaDatabase(List<Virtua> dataFromApi, List<Virtua> dataFromDb);
}
