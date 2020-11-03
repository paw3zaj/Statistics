package pl.pzdev2.scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.pzdev2.scan.BadScan;
import pl.pzdev2.scan.CorrectScan;
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
    private List<CorrectScan> correctScans = new ArrayList<>();

    private static final Logger LOG = LoggerFactory.getLogger(ScannerService.class);

    public ScannerService(VirtuaRepository virtuaRepository) {
        this.virtuaRepository = virtuaRepository;
    }

    @Override
    public List<CorrectScan> getCorrectScans() {
        return correctScans;
    }

    void addToCorrectScans(CorrectScan correctScan) {
        correctScans.add(correctScan);
    }

    @Override
    public List<ScannerData> extractBadBarcodes(List<ScannerData> barcodeList) {
        return barcodeList.stream()
                .filter(v -> {
                    Virtua virtua = virtuaRepository.findByBarcode(v.getBarcode());
                    if(virtua != null) {
                        addToCorrectScans(new CorrectScan(FormatDateTime.convertToLocalDateTime(v.getCreatedDate()), virtua));
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
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
    public List<BadScan> addAllToBadScans(List<ScannerData> badBarcodes) {
        var badScans = new ArrayList<BadScan>();
        for(ScannerData b : badBarcodes) {
            badScans.add(new BadScan(FormatDateTime.convertToLocalDateTime(b.getCreatedDate()), b.getBarcode()));
        }
        return badScans;
    }
}
