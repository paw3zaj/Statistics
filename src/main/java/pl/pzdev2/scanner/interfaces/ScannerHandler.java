package pl.pzdev2.scanner.interfaces;

import pl.pzdev2.scanner.ScannerData;

import java.util.List;

public interface ScannerHandler {

boolean receiveScans(List<ScannerData> barcodeList) throws InterruptedException;
}
