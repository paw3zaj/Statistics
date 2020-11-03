package pl.pzdev2.scan.interfaces;

import pl.pzdev2.scan.BadScan;
import pl.pzdev2.scan.CorrectScan;
import pl.pzdev2.scanner.ScannerData;

import java.util.List;

public interface ScanHandler {

    void saveBadScans(List<BadScan> badScans);
    void saveCorrectScan(List<CorrectScan> correctScans);
}
