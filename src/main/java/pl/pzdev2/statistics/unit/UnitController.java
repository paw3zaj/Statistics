package pl.pzdev2.statistics.unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pzdev2.utility.DateTimeUtility;
import pl.pzdev2.virtua.Virtua;

@Controller
public class UnitController {

    private final UnitHandler unitHandler;
    private static final Logger LOG = LoggerFactory.getLogger(UnitController.class);

    public UnitController(UnitHandler unitHandler) {
        this.unitHandler = unitHandler;
    }

    @GetMapping("/unit")
    public String unit(@RequestParam(value = "barcode", required = false) String barcode,
                       Model model) {

        Virtua virtua = null;

        try {
            virtua = unitHandler.findByBarcode(barcode);
        } catch (Exception e) {
            LOG.info("Pusty String jako barcode. {}", DateTimeUtility.getDateTimeAsString());
            e.printStackTrace();
        }


        if(virtua != null) {

            var auditLogs = unitHandler.findVirtuaAuditLogsByBarcode(barcode);
            var periods = unitHandler.countPerMonth(barcode);

            model.addAttribute("author", virtua.getAuthor());
            model.addAttribute("title", virtua.getTitle());
            model.addAttribute("signature", virtua.getSignature());
            model.addAttribute("createdDate", virtua.getCreatedDate());
            model.addAttribute("barcode", virtua.getBarcode());
            model.addAttribute("status", virtua.getStatus());

            model.addAttribute("virtuaAuditLogs", auditLogs);

            model.addAttribute("periods", periods);
        }
        return "unit";
    }
}
