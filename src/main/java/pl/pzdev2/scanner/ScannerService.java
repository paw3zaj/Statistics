package pl.pzdev2.scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.pzdev2.scan.Scan;
import pl.pzdev2.scanner.interfaces.ScannerHandler;
import pl.pzdev2.utility.FormatDateTime;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScannerService implements ScannerHandler {

    private VirtuaRepository virtuaRepository;
    private List<Scan> scans;// = new ArrayList<>();

    private static final String CORRECT = "correct";
    private static final String BAD = "bad";

    private static final Logger LOG = LoggerFactory.getLogger(ScannerService.class);

    public ScannerService(VirtuaRepository virtuaRepository) {
        this.virtuaRepository = virtuaRepository;
    }

    @Override
    public List<Scan> getScans() {
        return scans;
    }

    void addToScans(Scan scan) {
        scans.add(scan);
    }



    @Override
    public List<ScannerData> barcodeMapping(String json) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        List<ScannerData> scansList = objectMapper.readValue(json, new TypeReference<>() {});
        LOG.info("Liczba wykonanych skanów: {}  przesłana na serwer: {}",
                scansList.size(), FormatDateTime.getDateTime());
        return scansList;
    }

    @Override
    public void makeScanList(List<ScannerData> barcodeList) {
        scans = new ArrayList<>();

        barcodeList.forEach(v -> {
            Virtua virtua = virtuaRepository.findByBarcode(v.getBarcode());
            String scanType = CORRECT;
            if (virtua == null) {
                scanType = BAD;
            }
            addToScans(new Scan(FormatDateTime.convertToLocalDateTime(v.getCreatedDate()), virtua, scanType));
        });
    }


////    @Override
//    public List<ScannerData> extractBadBarcodes(List<ScannerData> barcodeList) {
//        return barcodeList.stream()
//                .filter(v -> {
//                    Virtua virtua = virtuaRepository.findByBarcode(v.getBarcode());
//                    if(virtua != null) {
//                        addToCorrectScans(new CorrectScan(FormatDateTime.convertToLocalDateTime(v.getCreatedDate()), virtua));
//                        return false;
//                    }
//                    return true;
//                })
//                .collect(Collectors.toList());
//    }
//
////    @Override
//    public List<BadScan> addAllToBadScans(List<ScannerData> badBarcodes) {
//        var badScans = new ArrayList<BadScan>();
//        for(ScannerData b : badBarcodes) {
//            badScans.add(new BadScan(FormatDateTime.convertToLocalDateTime(b.getCreatedDate()), b.getBarcode()));
//        }
//        return badScans;
//    }
}
