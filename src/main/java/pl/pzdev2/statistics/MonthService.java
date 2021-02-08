package pl.pzdev2.statistics;

import org.springframework.stereotype.Service;
import pl.pzdev2.scan.CorrectScan;
import pl.pzdev2.scan.interfaces.BadScanRepository;
import pl.pzdev2.scan.interfaces.CorrectScanRepository;
import pl.pzdev2.scan.interfaces.ScanRepository;
import pl.pzdev2.statistics.interfaces.Month;
import pl.pzdev2.statistics.interfaces.MonthHandler;
import pl.pzdev2.virtua.Virtua;

import java.util.*;

@Service
public class MonthService implements MonthHandler {

    private ScanRepository scanRepository;
    private BadScanRepository badScanRepository;
    private CorrectScanRepository correctScanRepository;

    public MonthService(ScanRepository scanRepository, BadScanRepository badScanRepository, CorrectScanRepository correctScanRepository) {
        this.scanRepository = scanRepository;
        this.badScanRepository = badScanRepository;
        this.correctScanRepository = correctScanRepository;
    }

    @Override
    public int countBadScans(int year, int month) {
        return badScanRepository.countIncorrectScansInMonth(year, month);
    }

    @Override
    public int countAllScans(int year, int month) {
        return scanRepository.countAllScansInMonth(year, month);
    }

    @Override
    public List<CorrectScan> getAllByMonth(int year, int month) {
        return correctScanRepository.findAllCorrectScansInMonth(year, month);
    }

    @Override
    public List<CorrectScan> getAllByYear(int year) {
        return correctScanRepository.findAllCorrectScansInYear(year);
    }

    @Override
    public Map<Virtua, Integer> groupingOfScans(List<CorrectScan> allScans) {

        var counter = new HashMap<Virtua, Integer>();
        for (CorrectScan scan : allScans) {
            counter.merge(scan.getVirtua(), 1, Integer::sum);
        }
        return counter;
    }

    @Override
    public Map<Virtua, Integer> sortScans(Map<Virtua, Integer> groupedScans) {

        var reverseSortedMap = new LinkedHashMap<Virtua, Integer>();
        groupedScans.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(s -> reverseSortedMap.put(s.getKey(), s.getValue()));
        return reverseSortedMap;
    }

    @Override
    public List<Month> countTotal(int year, int month) {

        System.out.println("start service");
        List<Month> list = correctScanRepository.countTotalScansInMonth(year, month);
        System.out.println("end service");
        return list;
    }
}