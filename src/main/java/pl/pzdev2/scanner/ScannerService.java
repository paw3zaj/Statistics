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

import java.util.*;

@Service
public class ScannerService implements ScannerHandler {

    private final BarcodeComparator barcodeComparator;
    private final FileHandler fileHandler;
    private final VirtuaRepository virtuaRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ScannerService.class);

    public ScannerService(BarcodeComparator barcodeComparator,
                          FileHandler fileHandler, VirtuaRepository virtuaRepository) {
        this.barcodeComparator = barcodeComparator;
        this.fileHandler = fileHandler;
        this.virtuaRepository = virtuaRepository;
    }

    @Override
    public List<ScannerData> barcodeMapping(String json) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        List<ScannerData> scansList = objectMapper.readValue(json, new TypeReference<>() {
        });
        LOG.info("Liczba wykonanych skanów: {}  przesłana na serwer: {}",
                scansList.size(), FormatDateTime.getDateTimeAsString());
        return scansList;
    }

    @Override
    public List<ScannerData> removeDuplicateScans(List<ScannerData> fromScanner) {

        List<ScannerData> fromFile = fileHandler.readFile();
        fileHandler.writeFile(fromScanner);
        fromScanner.removeAll(fromFile);

        return fromScanner;
    }

    @Override
    public List<Scan> convertToScans(List<ScannerData> scannerDataList) {

        List<Scan> scans = new LinkedList<>();
        ScannerData[] sData = convertListToTable(scannerDataList);
        int lenght = sData.length;

        for(int i = 0; i < lenght; i++) {
            Virtua virtua = virtuaRepository.findByBarcode(sData[i].getBarcode());
            if (virtua == null) {
                if(i > 0) {
                    if(barcodeComparator.checkTheScans(sData[i], sData[i-1])) {
                        continue;
                    }
                }
                if (i < lenght - 1) {
                    if(barcodeComparator.checkTheScans(sData[i], sData[i+1])) {
                        continue;
                    }
                }
            }
            scans.add(new Scan(
                    virtua,
                    FormatDateTime.getYear(sData[i].getCreatedDate()),
                    FormatDateTime.getMonthValue(sData[i].getCreatedDate())));
        }
        return scans;
    }

    public ScannerData[] convertListToTable(List<ScannerData> scannerDataList) {
        int size = scannerDataList.size();
        ScannerData[] scannerDataTable = new ScannerData[size];
        int i = 0;
        for (ScannerData scannerData : scannerDataList) {
            scannerDataTable[i] = scannerData;
            i++;
        }

        return scannerDataTable;
    }
}