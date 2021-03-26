package pl.pzdev2.statistics.unit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pzdev2.virtua.Virtua;

@Controller
public class UnitController {

    private final UnitHandler unitHandler;

    public UnitController(UnitHandler unitHandler) {
        this.unitHandler = unitHandler;
    }

    @GetMapping("/unit")
    public String unit(@RequestParam(defaultValue = "") String barcode, Model model) {

        if (!barcode.equals("")) {
            var virtua = unitHandler.findByBarcode(barcode);

            if (virtua != null) {
                virtua.setStatusValue();
                var auditLogs = unitHandler.findVirtuaAuditLogsByBarcode(barcode);
                var periods = unitHandler.countPerMonth(barcode);

                model.addAttribute("virtua", virtua);
                model.addAttribute("virtuaAuditLogs", auditLogs);
                model.addAttribute("periods", periods);
                return "unit";
            }
        }

        model.addAttribute("virtua", new Virtua());
        return "unit";
    }
}
