package pl.pzdev2.statistics.unit;

import org.springframework.stereotype.Service;
import pl.pzdev2.scan.interfaces.ScanRepository;
import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.VirtuaAuditLog;
import pl.pzdev2.virtua.VirtuaLog;
import pl.pzdev2.virtua.interfaces.VirtuaAuditLogRepository;
import pl.pzdev2.virtua.interfaces.VirtuaRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class UnitService implements UnitHandler {

    private VirtuaRepository virtuaRepository;
    private ScanRepository scanRepository;
    private VirtuaAuditLogRepository virtuaAuditLogRepository;
    private Virtua virtua = new Virtua();
    private List<VirtuaAuditLog> auditLogs = new LinkedList<>();

    public UnitService(VirtuaRepository virtuaRepository,
                       ScanRepository scanRepository, VirtuaAuditLogRepository virtuaAuditLogRepository) {
        this.virtuaRepository = virtuaRepository;
        this.scanRepository = scanRepository;
        this.virtuaAuditLogRepository = virtuaAuditLogRepository;
    }

    @Override
    public Virtua findVirtuaByBarcode(String barcode) {
        return virtuaRepository.findByBarcode(barcode);
    }

    @Override
    public int countPerMonth(int year, int month, String barcode) {
        return scanRepository.countBarcodeScansForTheMonth(year, month, barcode);
    }

    @Override
    public List<VirtuaAuditLog> findVirtuaAuditLogsByIdVirtua(Long idVirtua) {
//        auditLogs.clear();
//        auditLogs.addAll(virtuaAuditLogRepository.findByIdVirtua(idVirtua));
//        return auditLogs;
        return virtuaAuditLogRepository.findByIdVirtua(idVirtua);
    }

    @Override
    public Virtua getVirtua() {
        return virtua;
    }
    @Override
    public void setVirtua(Virtua virtua) {
        this.virtua = virtua;
    }

    @Override
    public List<VirtuaAuditLog> getAuditLogs() {
        return auditLogs;
    }
    @Override
    public void setAuditLogs(List<VirtuaAuditLog> auditLogs) {
        this.auditLogs = auditLogs;
    }
}
