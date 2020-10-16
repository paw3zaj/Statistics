package pl.pzdev2.virtua.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import pl.pzdev2.virtua.Virtua;

public interface DataFetch {
	
	Scanner getDataFromApi(String path) throws IOException;
	List<Virtua> getDataFromDatabase();
}
