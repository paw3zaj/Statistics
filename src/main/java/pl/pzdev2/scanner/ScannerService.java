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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ScannerService implements ScannerHandler {

    private final VirtuaRepository virtuaRepository;

    private static final String filePath = System.getenv("filePath");
    private static final Logger LOG = LoggerFactory.getLogger(ScannerService.class);

    public ScannerService(VirtuaRepository virtuaRepository) {
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

        List<ScannerData> fromFile = readFile();
        writeFile(fromScanner);
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
                    if(checkTheScans(sData[i], sData[i-1])) {
                        continue;
                    }
                }
                if (i < lenght - 1) {
                    if(checkTheScans(sData[i], sData[i+1])) {
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

    private void writeFile(List<ScannerData> list) {
        try (var out = new PrintWriter(
                filePath, StandardCharsets.UTF_8)) {
            writeAllScannerData(list, out);
        } catch (IOException e) {
            LOG.info("Problem z zapisem do pliku: {}", FormatDateTime.getDateTimeAsString());
            e.printStackTrace();
        }
    }

    private List<ScannerData> readFile() {
        List<ScannerData> list = null;
        try (var in = new Scanner(
                new FileInputStream(filePath), StandardCharsets.UTF_8)) {
            list = readAllScannerData(in);
        } catch (FileNotFoundException e) {
            LOG.info("Problem z odczytaniem pliku: {}", FormatDateTime.getDateTimeAsString());
            e.printStackTrace();
        }
        return list;
    }

    private void writeAllScannerData(List<ScannerData> sData, PrintWriter out) {
        for (ScannerData sD : sData) {
            writeScannerData(out, sD);
        }
    }

    private List<ScannerData> readAllScannerData(Scanner in) {

        List<ScannerData> list = new LinkedList<>();
        while (in.hasNextLine()) {
            list.add(readScannerData(in));
        }
        return list;
    }

    private void writeScannerData(PrintWriter out, ScannerData sD) {
        out.println(sD.getBarcode() + "|" + sD.getCreatedDate());
    }

    private ScannerData readScannerData(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        return new ScannerData(tokens[0], tokens[1]);
    }

    public boolean shortTimeLapse(ScannerData error, ScannerData correct) {

        LocalDateTime errorTime = FormatDateTime.convertStringToLocalDateTime(error.getCreatedDate());
        LocalDateTime correctTime = FormatDateTime.convertStringToLocalDateTime(correct.getCreatedDate());

        Duration timeElapsed = Duration.between(errorTime, correctTime);
        long second = timeElapsed.toSeconds();

        if(Math.abs(second) > 1l){
            return false;
        }
        return true;
    }

    public boolean compareBarcode(ScannerData error, ScannerData correct) {

        char[] errBarcode = error.getBarcode().toCharArray();
        char[] corBarcode = correct.getBarcode().toCharArray();

        int counter = 0;

        for (int i = 0; i < corBarcode.length; i++) {
            if (corBarcode[i] != errBarcode[i]) {
                counter++;
            }
            if (counter > 3) {
                return false;
            }
        }
        return true;
    }

    public boolean checkTheScans(ScannerData error, ScannerData correct) {
        if(shortTimeLapse(error, correct)){
            if(compareBarcode(error, correct)){
                return true;
            }
        }
        return false;
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