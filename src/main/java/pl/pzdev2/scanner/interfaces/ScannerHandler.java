package pl.pzdev2.scanner.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.pzdev2.scanner.ScannerData;

import java.util.List;

public interface ScannerHandler {

    List<ScannerData> deserializationScans(String json) throws JsonProcessingException;
    List<ScannerData> extractCorrectScans(List<ScannerData> barcodeList);
    List<ScannerData> getBadScans();
}
