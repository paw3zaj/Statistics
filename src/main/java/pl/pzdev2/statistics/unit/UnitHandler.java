package pl.pzdev2.statistics.unit;

import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.VirtuaLog;

import java.util.List;

public interface UnitHandler {

    Virtua findVirtuaByBarcode(String barcode);
    int countPerMonth(int year, int month, String barcode);
    List<VirtuaLog> findVirtuaLogsByBarcode(String barcode);
}
