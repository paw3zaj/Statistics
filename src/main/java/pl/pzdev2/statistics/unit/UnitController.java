package pl.pzdev2.statistics.unit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pzdev2.virtua.Virtua;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Controller
public class UnitController {

    private UnitHandler unitHandler;
    private List<Period> periods = new LinkedList<>();
//    private Integer[] months = new Integer[13];
//    private Virtua virtua = new Virtua();
    public UnitController(UnitHandler unitHandler) {
        this.unitHandler = unitHandler;
    }

//    @GetMapping("/countBorrowing")
//    public int countTheNumberOfBorrows(int year, int month, String barcode) {
//        return unitHandler.countPerMonth(year, month, barcode);
//    }

    @GetMapping("/unit")
    public String unit(@RequestParam(value = "barcode", required = false) String barcode,
                       Model model) {

//        var virtua = unitHandler.getVirtua();

        var virtua = unitHandler.findVirtuaByBarcode(barcode);
//        model.addAttribute("virtua", virtua);
        model.addAttribute("author", virtua.getAuthor());
        model.addAttribute("title", virtua.getTitle());
        model.addAttribute("signature", virtua.getSignature());
        model.addAttribute("createdDate", virtua.getCreatedDate());
        model.addAttribute("barcode", virtua.getBarcode());

        var auditLogs = unitHandler.findVirtuaAuditLogsByIdVirtua(virtua.getIdVirtua());

//        var auditLogs = unitHandler.getAuditLogs();

        model.addAttribute("virtuaAuditLogs", auditLogs);

        int thisYear = LocalDate.now().getYear();

        periods.clear();

        for(int y = 2020; y <= thisYear; y++){
//            var months = new Integer[13];
            var period = new Period();
            var months = period.getMonths();
            months[0] = y;
            for(int m = 1; m <= 12; m++) {
                months[m] = unitHandler.countPerMonth(y, m, virtua.getBarcode());
            }
//            period.print();
            periods.add(period);
        }

        model.addAttribute("periods", periods);

        return "unit";
    }

//    @PostMapping("/setBarcode")
//    public String chosePeriod(@ModelAttribute Virtua v, BindingResult bindingResult) {
//        if(bindingResult.hasErrors()){
//            return "unit";
//        }
//        var barcode = v.getBarcode();
//        var virtua = unitHandler.findVirtuaByBarcode(barcode);
//
//        System.out.println("virtua: " + virtua);
//        if(virtua == null && v == null) {
//            return "unit";
//        }
//
//        unitHandler.setVirtua(virtua);
//
//        unitHandler.findVirtuaAuditLogsByIdVirtua(unitHandler.getVirtua().getIdVirtua());
//
//        return "redirect:/unit";
//    }
}
