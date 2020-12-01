package pl.pzdev2.statistics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.statistics.interfaces.ItemHandler;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.VirtuaLog;

import java.util.List;

@RestController
public class ItemController {

    private ItemHandler itemHandler;

    public ItemController(ItemHandler itemHandler) {
        this.itemHandler = itemHandler;
    }

    @GetMapping("/getVirtuaByBarcode")
    public Virtua getVirtuaByBarcode(String barcode) {
        return itemHandler.findVirtuaByBarcode(barcode);
    }

    @GetMapping("/countBorrowing")
    public int countTheNumberOfBorrows(String barcode, int year, int month) {
        return itemHandler.countPerMonth(barcode, year, month);
    }

    @GetMapping("/getVirtuaLogsByBarcode")
    public List<VirtuaLog> getVirtuaLogsByBarcode(String barcode) {
        return itemHandler.findVirtuaLogsByBarcode(barcode);
    }
}
