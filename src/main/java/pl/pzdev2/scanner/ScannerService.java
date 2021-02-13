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

    private static final Logger LOG = LoggerFactory.getLogger(ScannerService.class);

    public ScannerService(VirtuaRepository virtuaRepository) {
        this.virtuaRepository = virtuaRepository;
    }

    @Override
    public List<ScannerData> barcodeMapping(String json) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        List<ScannerData> scansList = objectMapper.readValue(json, new TypeReference<>() {});
        LOG.info("Liczba wykonanych skanów: {}  przesłana na serwer: {}",
                scansList.size(), FormatDateTime.getDateTimeAsString());
        return scansList;
    }

    @Override
    public List<Scan> createAListOfScans(List<ScannerData> barcodeList) {
        scans = new LinkedList<>();

        barcodeList.forEach(s -> {
            var virtua = virtuaRepository.findByBarcode(s.getBarcode());
            scans.add(new Scan(
                    virtua,
                    FormatDateTime.getYear(s.getCreatedDate()),
                    FormatDateTime.getMonthValue(s.getCreatedDate())));
        });
        return scans;
    }
}
