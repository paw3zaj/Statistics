package pl.pzdev2.statistics;

import org.springframework.stereotype.Service;
import pl.pzdev2.scan.Scan;
import pl.pzdev2.scan.interfaces.ScanRepository;
import pl.pzdev2.statistics.interfaces.Month;
import pl.pzdev2.statistics.interfaces.MonthHandler;

import java.util.*;

@Service
public class MonthService implements MonthHandler {

    private ScanRepository scanRepository;

    public MonthService(ScanRepository scanRepository) {
        this.scanRepository = scanRepository;
    }

    @Override
    public int countBadScans(int year, int month) {
        return scanRepository.countIncorrectScansInMonth(year, month);
    }

    @Override
    public int countAllScans(int year, int month) {
        return scanRepository.countAllScansInMonth(year, month);
    }

    @Override
    public List<Scan> getAllByMonth(int year, int month) {
        return scanRepository.findAllCorrectScansInMonth(year, month);
    }

    @Override
    public List<Scan> getAllByYear(int year) {
        return scanRepository.findAllCorrectScansInYear(year);
    }

    @Override
    public List<Month> countTotal(int year, int month) {
        return scanRepository.countTotalScansInMonth(year, month);
    }
}