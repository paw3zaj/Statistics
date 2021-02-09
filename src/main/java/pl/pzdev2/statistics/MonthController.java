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

    @GetMapping("/countAllBadScans")
    int countAllBadScans(int year, int month) {
        return monthHandler.countAllIncorrectScans(year, month);
    }

    @GetMapping("/countAllScans")
    int countAllScans(int year, int month) {
        return monthHandler.countAllScans(year, month);
    }

    @GetMapping("/countTotal")
    List<Month> countTotal(int year, int month) {
        return monthHandler.countTotalScans(year, month);
    }
}
