package pl.pzdev2.scan;

import org.springframework.stereotype.Service;
import pl.pzdev2.scan.interfaces.BadScanRepository;
import pl.pzdev2.scan.interfaces.CorrectScanRepository;
import pl.pzdev2.scan.interfaces.ScanHandler;

import java.util.List;

@Service
public class ScanService implements ScanHandler {

    private BadScanRepository badScanRepository;
    private CorrectScanRepository correctScanRepository;

    public ScanService(BadScanRepository badScanRepository, CorrectScanRepository correctScanRepository) {
        this.badScanRepository = badScanRepository;
        this.correctScanRepository = correctScanRepository;
    }

    @Override
    public void saveBadScans(List<BadScan> badScans) {
        badScanRepository.saveAll(badScans);
    }

    @Override
    public void saveCorrectScan(List<CorrectScan> correctScans) {
        correctScanRepository.saveAll(correctScans);
    }

}