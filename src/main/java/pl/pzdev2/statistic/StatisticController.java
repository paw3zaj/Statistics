package pl.pzdev2.statistic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.pzdev2.virtua.VirtuaLogs;
import pl.pzdev2.virtua.interfaces.VirtuaLogsRepository;

import java.util.List;

@Controller
public class StatisticController {

    private VirtuaLogsRepository virtuaLogsRepository;

    public StatisticController(VirtuaLogsRepository virtuaLogsRepository) {
        this.virtuaLogsRepository = virtuaLogsRepository;
    }

    @ModelAttribute("allVirtuaLogs")
    public List<VirtuaLogs> getAllVirtuaLogs() {
        return virtuaLogsRepository.findAll();
    }

    @GetMapping("/")
    public String getStatistics() {
        return "statistics";
    }
}
