package pl.pzdev2.statistics.unit;

import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.VirtuaAuditLog;

import java.util.List;

public interface UnitHandler {

    Virtua findVirtuaByBarcode(String barcode);
    int countPerMonth(int year, int month, String barcode);
    List<VirtuaAuditLog> findVirtuaAuditLogsByIdVirtua(Long idVirtua);
    void setVirtua(Virtua virtua);
    Virtua getVirtua();
    List<VirtuaAuditLog> getAuditLogs();
    void setAuditLogs(List<VirtuaAuditLog> auditLogs);
}
