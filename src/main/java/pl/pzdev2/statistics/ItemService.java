package pl.pzdev2.statistics;

import org.springframework.stereotype.Service;
import pl.pzdev2.scan.interfaces.CorrectScanRepository;
import pl.pzdev2.statistics.interfaces.ItemHandler;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.VirtuaLog;
import pl.pzdev2.virtua.interfaces.VirtuaLogRepository;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

import java.util.List;

@Service
public class ItemService implements ItemHandler {

    private VirtuaRepository virtuaRepository;
    private CorrectScanRepository correctScanRepository;
    private VirtuaLogRepository virtuaLogRepository;

    public ItemService(VirtuaRepository virtuaRepository,
                       CorrectScanRepository correctScanRepository,
                       VirtuaLogRepository virtuaLogRepository) {
        this.virtuaRepository = virtuaRepository;
        this.correctScanRepository = correctScanRepository;
        this.virtuaLogRepository = virtuaLogRepository;
    }

    @Override
    public Virtua findVirtuaByBarcode(String barcode) {
        return virtuaRepository.findByBarcode(barcode);
    }

    @Override
    public int countPerMonth(String barcode, int year, int month) {
        return correctScanRepository.countMonthlyBookBorrowing(barcode, year, month);
    }

    @Override
    public List<VirtuaLog> findVirtuaLogsByBarcode(String barcode) {
        return virtuaLogRepository.findByBarcode(barcode);
    }
}