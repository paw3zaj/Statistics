package pl.pzdev2.scanner.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.pzdev2.scan.Scan;
import pl.pzdev2.scanner.ScannerData;

import java.util.List;

public interface ScannerHandler {

    List<ScannerData> barcodeMapping(String json) throws JsonProcessingException;
    void makeScanList(List<ScannerData> barcodeList);
    List<Scan> getScans();
}
