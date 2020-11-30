package pl.pzdev2.statistics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.scan.CorrectScan;
import pl.pzdev2.statistics.interfaces.StatisticsHandler;
import pl.pzdev2.virtua.Virtua;

import java.util.List;
import java.util.Map;

@RestController
public class StatisticsController {

    private StatisticsHandler statisticsHandler;

    public StatisticsController(StatisticsHandler statisticsHandler) {
        this.statisticsHandler = statisticsHandler;
    }

    @GetMapping("/countBadScans")
    int countIncorrectScansInMonth(int year, int month) {
        return statisticsHandler.countBadScans(year, month);
    }

    @GetMapping("/countAllScans")
    int countAllScansInMonth(int year, int month) {
        return statisticsHandler.countAllScans(year, month);
    }

    @GetMapping("/getAllInMonth")
    Map<Virtua, Integer> getAll(int year, int month) {
        List<CorrectScan> scans = statisticsHandler.getAllByMonth(year, month);
        Map<Virtua, Integer> groupedScans = statisticsHandler.groupingOfScans(scans);
        return statisticsHandler.sortScans(groupedScans);
    }

//    @GetMapping("/getAllInYear")
//    Map<Virtua, Integer> getAllInYear(int year) {
//        List<CorrectScan> scans = statisticsHandler.getAllByYear(year);
//        Map<Virtua, Integer> groupedScans = statisticsHandler.groupingOfScans(scans);
//        Map<Virtua, Integer> sortedScans = statisticsHandler.sortScans(groupedScans);
//
//        return sortedScans;
//    }
}
