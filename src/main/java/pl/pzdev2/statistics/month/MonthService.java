package pl.pzdev2.statistics.month;

import org.springframework.stereotype.Service;
import pl.pzdev2.scan.interfaces.ScanRepository;

import java.util.*;

@Service
public class MonthService implements MonthHandler {

    private final ScanRepository scanRepository;

    public MonthService(ScanRepository scanRepository) {
        this.scanRepository = scanRepository;
    }

    @Override
    public int countAllScans(int year, int month) {
        if(month == 0) {
            return scanRepository.countAllScansForTheYear(year);
        }
        return scanRepository.countAllScansForTheMonth(year, month);
    }

    @Override
    public int countAllIncorrectScans(int year, int month) {
        if(month == 0) {
            return scanRepository.countAllIncorrectScansForTheYear(year);
        }
        return scanRepository.countAllIncorrectScansForTheMonth(year, month);
    }

    @Override
    public List<MonthTable> countTotalScans(int year, int month) {
        if(month == 0) {
            return scanRepository.countTotalScansForTheYear(year);
        }
        return scanRepository.countTotalScansForTheMonth(year, month);
    }
}