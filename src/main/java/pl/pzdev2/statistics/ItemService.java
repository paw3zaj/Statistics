package pl.pzdev2.statistics;

import org.springframework.stereotype.Service;
import pl.pzdev2.scan.interfaces.ScanRepository;
import pl.pzdev2.statistics.interfaces.ItemHandler;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.VirtuaLog;
import pl.pzdev2.virtua.interfaces.VirtuaLogRepository;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

import java.util.List;

@Service
public class ItemService implements ItemHandler {

    private VirtuaRepository virtuaRepository;
    private ScanRepository scanRepository;
    private VirtuaLogRepository virtuaLogRepository;

    public ItemService(VirtuaRepository virtuaRepository,
                       ScanRepository scanRepository,
                       VirtuaLogRepository virtuaLogRepository) {
        this.virtuaRepository = virtuaRepository;
        this.scanRepository = scanRepository;
        this.virtuaLogRepository = virtuaLogRepository;
    }

    @Override
    public Virtua findVirtuaByBarcode(String barcode) {
        return virtuaRepository.findByBarcode(barcode);
    }

    @Override
    public int countPerMonth(int year, int month, String barcode) {
        return scanRepository.countBookScansForTheMonth(year, month, barcode);
    }

    @Override
    public List<VirtuaLog> findVirtuaLogsByBarcode(String barcode) {
        return virtuaLogRepository.findByBarcode(barcode);
    }
}
