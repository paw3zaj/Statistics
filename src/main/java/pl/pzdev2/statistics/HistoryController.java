package pl.pzdev2.statistics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.statistics.interfaces.HistoryHandler;
import pl.pzdev2.virtua.VirtuaLog;

import java.util.List;

@RestController
public class HistoryController {

    private HistoryHandler historyHandler;

    public HistoryController(HistoryHandler historyHandler) {
        this.historyHandler = historyHandler;
    }

    @GetMapping("/getAllVirtuaLogs")
    public List<VirtuaLog> getAll() {
        return historyHandler.findAll();
    }
}
