package pl.pzdev2.scan;

import org.springframework.stereotype.Service;
import pl.pzdev2.scan.interfaces.ScanHandler;
import pl.pzdev2.scan.interfaces.ScanRepository;

import java.util.List;

@Service
public class ScanService implements ScanHandler {

    private final ScanRepository scanRepository;

    public ScanService(ScanRepository scanRepository) {
        this.scanRepository = scanRepository;
    }

    @Override
    public void saveScans(List<Scan> scans) {
        scanRepository.saveAll(scans);
    }
}