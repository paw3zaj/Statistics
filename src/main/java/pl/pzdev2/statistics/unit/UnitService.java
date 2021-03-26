package pl.pzdev2.statistics.unit;

import org.springframework.stereotype.Service;
import pl.pzdev2.scan.interfaces.ScanRepository;
import pl.pzdev2.utility.DateTimeUtility;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.VirtuaAuditLog;
import pl.pzdev2.virtua.interfaces.VirtuaAuditLogRepository;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class UnitService implements UnitHandler {

    private final VirtuaRepository virtuaRepository;
    private final ScanRepository scanRepository;
    private final VirtuaAuditLogRepository virtuaAuditLogRepository;

    public UnitService(VirtuaRepository virtuaRepository,
                       ScanRepository scanRepository, VirtuaAuditLogRepository virtuaAuditLogRepository) {
        this.virtuaRepository = virtuaRepository;
        this.scanRepository = scanRepository;
        this.virtuaAuditLogRepository = virtuaAuditLogRepository;
    }

    @Override
    public Virtua findByBarcode(String barcode) {
        return virtuaRepository.findByBarcode(barcode);
    }

    @Override
    public List<Period> countPerMonth(String barcode) {

        var periods = new LinkedList<Period>();
        var thisYear = DateTimeUtility.getTheCurrentYear();

        for(int y = 2020; y <= thisYear; y++){
            var period = new Period();
            var months = period.getMonths();
            months[0] = y;
            for(int m = 1; m <= 12; m++) {
                months[m] = scanRepository.countBarcodeScansForTheMonth(y, m, barcode);
            }
            periods.add(period);
        }
        return periods;
    }

    @Override
    public List<VirtuaAuditLog> findVirtuaAuditLogsByBarcode(String barcode) {
        return virtuaAuditLogRepository.findByBarcode(barcode);
    }
}
