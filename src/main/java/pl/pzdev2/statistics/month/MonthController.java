package pl.pzdev2.statistics.month;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pzdev2.statistics.UtilityService;

import java.util.List;

@Controller
public class MonthController {

    private MonthHandler monthHandler;

    public MonthController(MonthHandler monthHandler) {
        this.monthHandler = monthHandler;
    }

    @ModelAttribute("allYears")
    public List<Integer> allYears() {
        return UtilityService.getYears();
    }

    @ModelAttribute("allMonths")
    public List<Month> allMonths() {
        return List.of(Month.ALL);
    }

        @GetMapping("/")
    public String index(@ModelAttribute Period period){
        return "index";
    }

    @GetMapping("/month")
    public String month(Model model) {

        var period = monthHandler.getPeriod();
        var year = period.getYear();
        var month = period.getMonth().getValue();

        model.addAttribute("period", period);
        model.addAttribute("countAllScans", monthHandler.countAllScans(year, month));
        model.addAttribute("countAllBadScans", monthHandler.countAllIncorrectScans(year, month));
        model.addAttribute("countTotalScans", monthHandler.countTotalScans(year, month));
        return "index";
    }

    @PostMapping("/setPeriod")
    public String chosePeriod(final Period period, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "index";
        }
        monthHandler.setPeriod(period);
        return "redirect:/month";
    }
}