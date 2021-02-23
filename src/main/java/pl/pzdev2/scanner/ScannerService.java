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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Service
public class ScannerService implements ScannerHandler {

    private final VirtuaRepository virtuaRepository;
    private List<Scan> scans;

    private static final String filePath = "data/recentScans.dat";
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
    public List<ScannerData> removeDuplicateScans(List<ScannerData> fromScanner) {

        List<ScannerData> fromFile = readFile();
        writeFile(fromScanner);
        fromScanner.removeAll(fromFile);

        return fromScanner;
    }

    @Override
    public List<Scan> convertToScans(List<ScannerData> scannerDataList) {
        scans = new LinkedList<>();

        scannerDataList.forEach(s -> {
            var virtua = virtuaRepository.findByBarcode(s.getBarcode());
            scans.add(new Scan(
                    virtua,
                    FormatDateTime.getYear(s.getCreatedDate()),
                    FormatDateTime.getMonthValue(s.getCreatedDate())));
        });
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
        try  (var in = new Scanner(
                new FileInputStream(filePath), StandardCharsets.UTF_8)){
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
}