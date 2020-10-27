package pl.pzdev2.scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.pzdev2.scanner.interfaces.ScannerHandler;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScannerService implements ScannerHandler {

    private VirtuaRepository virtuaRepository;
    private List<ScannerData> badScans = new ArrayList<>();

    public ScannerService(VirtuaRepository virtuaRepository) {
        this.virtuaRepository = virtuaRepository;
    }

    @Override
    public List<ScannerData> getBadScans() {
        return badScans;
    }

    void addToBadScans(ScannerData badScan) {
        badScans.add(badScan);
    }

    @Override
    public List<ScannerData> extractCorrectScans(List<ScannerData> barcodeList) {

        return barcodeList.stream()
                .filter(v -> {
                    Virtua virtua = virtuaRepository.findByBarcode(v.getBarcode());
                    if(virtua == null) {
                        addToBadScans(v);
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ScannerData> deserializationScans(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, new TypeReference<>() {});
    }
}
