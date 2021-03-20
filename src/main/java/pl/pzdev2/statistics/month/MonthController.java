package pl.pzdev2.statistics.month;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pzdev2.utility.DateTimeUtility;

import java.util.List;

@Controller
public class MonthController {

    private final MonthHandler monthHandler;

    public MonthController(MonthHandler monthHandler) {
        this.monthHandler = monthHandler;
    }

    @ModelAttribute("allYears")
    public List<Integer> allYears() {
        return DateTimeUtility.getYears();
    }

    @ModelAttribute("allMonths")
    public List<Month> allMonths() {
        return List.of(Month.ALL);
    }

    @GetMapping({"/","/month"})
    public String index(@ModelAttribute Period period){
        return "index";
    }

    @PostMapping("/month")
    public String chosePeriod(final Period period, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "redirect:/";
        }

        var year = period.getYear();
        var month = period.getMonth().getValue();

        model.addAttribute("countAllScans", monthHandler.countAllScans(year, month));
        model.addAttribute("countAllBadScans", monthHandler.countAllIncorrectScans(year, month));
        model.addAttribute("countTotalScans", monthHandler.countTotalScans(year, month));
        return "index";
    }
}