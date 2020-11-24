package pl.pzdev2.virtua;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.virtua.interfaces.VirtuaLogsRepository;

import java.util.List;

@RestController
public class VirtuaLogsController {

    private VirtuaLogsRepository virtuaLogsRepository;

    public VirtuaLogsController(VirtuaLogsRepository virtuaLogsRepository) {
        this.virtuaLogsRepository = virtuaLogsRepository;
    }

    @CrossOrigin
    @GetMapping("/allVirtuaLogs")
    List<VirtuaLogs> getAllVirtuaLogs() {
        return virtuaLogsRepository.findAll();
    }
}
