package pl.pzdev2.statistics.unit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.VirtuaLog;

import java.util.List;

@Controller
public class UnitController {

    private UnitHandler unitHandler;

    public UnitController(UnitHandler unitHandler) {
        this.unitHandler = unitHandler;
    }

//    @GetMapping("/getVirtuaByBarcode")
//    public Virtua getVirtuaByBarcode(String barcode) {
//        return unitHandler.findVirtuaByBarcode(barcode);
//    }
//
//    @GetMapping("/countBorrowing")
//    public int countTheNumberOfBorrows(int year, int month, String barcode) {
//        return unitHandler.countPerMonth(year, month, barcode);
//    }
//
//    @GetMapping("/getVirtuaLogsByBarcode")
//    public List<VirtuaLog> getVirtuaLogsByBarcode(String barcode) {
//        return unitHandler.findVirtuaLogsByBarcode(barcode);
//    }

    @GetMapping("/unit")
    public String unit() {
        return "unit";
    }
}
