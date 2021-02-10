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
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class ScannerService implements ScannerHandler {

    private VirtuaRepository virtuaRepository;
    private List<Scan> scans;

    private static final String CORRECT = "correct";
    private static final String BAD = "bad";

    private static final Logger LOG = LoggerFactory.getLogger(ScannerService.class);

    public ScannerService(VirtuaRepository virtuaRepository) {
        this.virtuaRepository = virtuaRepository;
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
    public List<Scan> createAListOfScans(List<ScannerData> barcodeList) {
        scans = new LinkedList<>();

        barcodeList.forEach(v -> {
            var virtua = virtuaRepository.findByBarcode(v.getBarcode());
            var scanType = CORRECT;
            if (virtua == null) {
                scanType = BAD;
            }
            scans.add(new Scan(FormatDateTime.convertToLocalDateTime(v.getCreatedDate()), virtua, scanType));
        });
        return scans;
    }
}
