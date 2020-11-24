package pl.pzdev2.scan;

import org.springframework.stereotype.Service;
import pl.pzdev2.scan.interfaces.BadScanRepository;
import pl.pzdev2.scan.interfaces.CorrectScanRepository;
import pl.pzdev2.scan.interfaces.ScanHandler;
import pl.pzdev2.scan.interfaces.ScanRepository;

import java.util.List;

@Service
public class ScanService implements ScanHandler {

    private ScanRepository scanRepository;
    private BadScanRepository badScanRepository;
    private CorrectScanRepository correctScanRepository;

    public ScanService(ScanRepository scanRepository, BadScanRepository badScanRepository, CorrectScanRepository correctScanRepository) {
        this.scanRepository = scanRepository;
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

    @Override
    public int countBadScans(int year, int month) {
        return badScanRepository.countIncorrectScansInMonth(year, month);
    }

    @Override
    public int countAllScans(int year, int month) {
        return scanRepository.countAllScansInMonth(year, month);
    }

}