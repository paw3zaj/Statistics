package pl.pzdev2.scan.interfaces;

import pl.pzdev2.scan.Scan;

import java.util.List;

public interface ScanHandler {

    void saveScans(List<Scan> scans);
}
