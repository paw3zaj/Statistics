package pl.pzdev2.virtua;

import org.springframework.stereotype.Service;
import pl.pzdev2.virtua.interfaces.VirtuaLogHandler;
import pl.pzdev2.virtua.interfaces.VirtuaLogRepository;

import java.util.List;

@Service
public class VirtuaLogService implements VirtuaLogHandler {

    private VirtuaLogRepository virtuaLogRepository;

    public VirtuaLogService(VirtuaLogRepository virtuaLogRepository) {
        this.virtuaLogRepository = virtuaLogRepository;
    }

    @Override
    public List<VirtuaLog> findAll() {
        return virtuaLogRepository.findAll();
    }

}
