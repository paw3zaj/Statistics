package pl.pzdev2.statistics;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.statistics.interfaces.Month;
import pl.pzdev2.statistics.interfaces.MonthHandler;

import java.util.List;

@CrossOrigin
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
    List<Month> getAll(int year, int month) {

        return monthHandler.countTotal(year, month);
    }

    @GetMapping("/getAllInYear")
    List<Month> getAll(int year) {
        return null;
    }
}
