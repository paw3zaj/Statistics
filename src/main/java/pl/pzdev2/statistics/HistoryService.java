package pl.pzdev2.statistics;

import org.springframework.stereotype.Service;
import pl.pzdev2.statistics.interfaces.HistoryHandler;
import pl.pzdev2.virtua.VirtuaLog;
import pl.pzdev2.virtua.interfaces.VirtuaLogRepository;

import java.util.List;

@Service
public class HistoryService implements HistoryHandler {

    private VirtuaLogRepository virtuaLogRepository;

    public HistoryService(VirtuaLogRepository virtuaLogRepository) {
        this.virtuaLogRepository = virtuaLogRepository;
    }

    @Override
    public List<VirtuaLog> findAll() {
        return virtuaLogRepository.findAll();
    }
}
