package pl.pzdev2.statistics;

import org.springframework.stereotype.Service;
import pl.pzdev2.scan.interfaces.CorrectScanRepository;
import pl.pzdev2.statistics.interfaces.ItemHandler;

@Service
public class ItemService implements ItemHandler {

    private CorrectScanRepository correctScanRepository;

    public ItemService(CorrectScanRepository correctScanRepository) {
        this.correctScanRepository = correctScanRepository;
    }

    @Override
    public int countPerMonth(String barcode, int year, int month) {
        return correctScanRepository.countMonthlyBookBorrowing(barcode, year, month);
    }
}
