package pl.pzdev2.statistics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.statistics.interfaces.ItemHandler;

@RestController
public class ItemController {

    private ItemHandler itemHandler;

    public ItemController(ItemHandler itemHandler) {
        this.itemHandler = itemHandler;
    }

    @GetMapping("/countBorrowing")
    public int countTheNumberOfBorrows(String barcode, int year, int month) {
        return itemHandler.countPerMonth(barcode, year, month);
    }
}
