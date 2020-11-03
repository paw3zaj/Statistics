package pl.pzdev2.scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.scan.BadScan;
import pl.pzdev2.scan.CorrectScan;
import pl.pzdev2.scan.interfaces.ScanHandler;
import pl.pzdev2.scanner.interfaces.ScannerHandler;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ScannerController {

    private final ScannerHandler scannerHandler;
    private final ScanHandler scanHandler;

    private static final Logger LOG = LoggerFactory.getLogger(ScannerController.class);

    public ScannerController(ScannerHandler scannerHandler, ScanHandler scanHandler) {
        this.scannerHandler = scannerHandler;
        this.scanHandler = scanHandler;
    }

    @PostMapping("/receive-books-barcode")
    public List<ScannerData> receiveScans(@RequestBody String json) {

        Runnable task = () -> {
            try {
                List<ScannerData> badBarcodes = scannerHandler.extractBadBarcodes(
                        scannerHandler.barcodeMapping(json));
                LOG.info("Liczba błędnych kodów kreskowych: {}",
                        badBarcodes.size());
                List<BadScan> badScans = scannerHandler.addAllToBadScans(badBarcodes);
                List<CorrectScan> correctScans = scannerHandler.getCorrectScans();

                scanHandler.saveBadScans(badScans);
                scanHandler.saveCorrectScan(correctScans);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


        };
        new Thread(task).start();

        return new ArrayList<>();
    }
}