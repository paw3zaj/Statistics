package pl.pzdev2.virtua;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.virtua.interfaces.VirtuaLogHandler;

import java.util.List;

@RestController
public class VirtuaLogController {

    private VirtuaLogHandler virtuaLogHandler;

    public VirtuaLogController(VirtuaLogHandler virtuaLogHandler) {
        this.virtuaLogHandler = virtuaLogHandler;
    }

    @GetMapping("/getAllVirtuaLogs")
    public List<VirtuaLog> getAll() {
        return virtuaLogHandler.findAll();
    }

}
