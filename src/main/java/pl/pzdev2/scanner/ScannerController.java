package pl.pzdev2.scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.scan.Scan;
import pl.pzdev2.scan.interfaces.ScanHandler;
import pl.pzdev2.scanner.interfaces.ScannerHandler;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ScannerController {

    private final ScannerHandler scannerHandler;
    private final ScanHandler scanHandler;

    public ScannerController(ScannerHandler scannerHandler, ScanHandler scanHandler) {
        this.scannerHandler = scannerHandler;
        this.scanHandler = scanHandler;
    }

    @PostMapping("/receive-books-barcode")
    public List<ScannerData> receiveScans(@RequestBody String json) {

        Runnable task = () -> {
            try {
                List<ScannerData> barcodeListFromScanner = scannerHandler.barcodeMapping(json);
                List<ScannerData> barcodeListAfterCompare = scannerHandler.removeDuplicateScans(barcodeListFromScanner);
                List<Scan> scans = scannerHandler.convertToScans(barcodeListAfterCompare);
                scanHandler.saveScans(scans);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        };
        new Thread(task).start();

        return new ArrayList<>();
    }
}