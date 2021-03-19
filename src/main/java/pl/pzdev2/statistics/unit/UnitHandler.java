package pl.pzdev2.statistics.unit;

import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.VirtuaAuditLog;

import java.util.List;

public interface UnitHandler {

    Virtua findByBarcode(String barcode);
    List<Period> countPerMonth(String barcode);
    List<VirtuaAuditLog> findVirtuaAuditLogsByBarcode(String barcode);
}
