package pl.pzdev2.statistics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.scan.CorrectScan;
import pl.pzdev2.statistics.interfaces.MonthHandler;
import pl.pzdev2.virtua.Virtua;

import java.util.List;
import java.util.Map;

@RestController
public class MonthController {

    private MonthHandler monthHandler;

    public MonthController(MonthHandler monthHandler) {
        this.monthHandler = monthHandler;
    }

    @GetMapping("/countBadScans")
    int countIncorrectScansInMonth(int year, int month) {
        return monthHandler.countBadScans(year, month);
    }

    @GetMapping("/countAllScans")
    int countAllScansInMonth(int year, int month) {
        return monthHandler.countAllScans(year, month);
    }

    @GetMapping("/getAllInMonth")
    Map<Virtua, Integer> getAll(int year, int month) {
        List<CorrectScan> scans = monthHandler.getAllByMonth(year, month);
        Map<Virtua, Integer> groupedScans = monthHandler.groupingOfScans(scans);
        return monthHandler.sortScans(groupedScans);
    }

//    @GetMapping("/getAllInYear")
//    Map<Virtua, Integer> getAllInYear(int year) {
//        List<CorrectScan> scans = monthHandler.getAllByYear(year);
//        Map<Virtua, Integer> groupedScans = monthHandler.groupingOfScans(scans);
//        Map<Virtua, Integer> sortedScans = monthHandler.sortScans(groupedScans);
//
//        return sortedScans;
//    }
}
