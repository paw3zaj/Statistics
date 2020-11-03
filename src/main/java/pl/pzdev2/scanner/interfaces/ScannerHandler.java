package pl.pzdev2.scanner.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.pzdev2.scan.BadScan;
import pl.pzdev2.scan.CorrectScan;
import pl.pzdev2.scanner.ScannerData;

import java.util.List;

public interface ScannerHandler {

    List<ScannerData> barcodeMapping(String json) throws JsonProcessingException;
    List<ScannerData> extractBadBarcodes(List<ScannerData> barcodeList);
    List<BadScan> addAllToBadScans(List<ScannerData> badBarcodes);
    List<CorrectScan> getCorrectScans();
}
