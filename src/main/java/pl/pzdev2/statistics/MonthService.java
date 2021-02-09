package pl.pzdev2.statistics;

import org.springframework.stereotype.Service;
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
    public List<Month> countTotalScans(int year, int month) {
        if(month == 0) {
            return scanRepository.countTotalScansForTheYear(year);
        }
        return scanRepository.countTotalScansForTheMonth(year, month);
    }
}