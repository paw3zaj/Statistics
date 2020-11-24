package pl.pzdev2.scan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.scan.interfaces.ScanHandler;

import java.util.List;

@RestController
public class ScanController {

    private ScanHandler scanHandler;

    public ScanController(ScanHandler scanHandler) {
        this.scanHandler = scanHandler;
    }

    @GetMapping("/countBadScans")
    int countIncorrectScansInMonth(int year, int month) {
        return scanHandler.countBadScans(year, month);
    }

    @GetMapping("/countAllScans")
    int countAllScansInMonth(int year, int month) {
        return scanHandler.countAllScans(year, month);
    }

}
