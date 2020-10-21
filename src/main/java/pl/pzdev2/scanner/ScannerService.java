package pl.pzdev2.scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.pzdev2.scanner.interfaces.ScannerHandler;
import pl.pzdev2.utility.FormatDateTime;

import java.util.List;

@Service
public class ScannerService implements ScannerHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ScannerService.class);

    @Override
    public boolean receiveScans(List<ScannerData> barcodeList) throws InterruptedException {

        task(barcodeList);
        LOG.info("Amount barcodes in bundle: {} uploads: {}", barcodeList.size(), FormatDateTime.getDateTime());
        return false;
    }

    public void task(List<ScannerData> barcodeList){// throws InterruptedException {

        Runnable task = () -> {
            barcodeList.forEach(b -> {
//                Virtua virtua = getByBarCode(b.getBarcode());
//                if (virtua != null) {
//                    correctScanRepository.save(new CorrectScan(FormatDateTime.convertToLocalDateTime(b.getCreatedDate()), virtua));
//                } else {
//                    badScanRepository.save(new BadScan(FormatDateTime.convertToLocalDateTime(b.getCreatedDate()), b.getBarcode()));
//                }
            });
        };
        new Thread(task).start();
    }

}
