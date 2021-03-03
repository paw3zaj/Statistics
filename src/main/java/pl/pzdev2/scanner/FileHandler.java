package pl.pzdev2.scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.pzdev2.utility.FormatDateTime;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Component
public class FileHandler {

    private static final String filePath = System.getenv("filePath");
    private static final Logger LOG = LoggerFactory.getLogger(FileHandler.class);

    void writeFile(List<ScannerData> list) {
        try (var out = new PrintWriter(
                filePath, StandardCharsets.UTF_8)) {
            writeAllScannerData(list, out);
        } catch (IOException e) {
            LOG.info("Problem z zapisem do pliku: {}", FormatDateTime.getDateTimeAsString());
            e.printStackTrace();
        }
    }

    List<ScannerData> readFile() {
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
}
