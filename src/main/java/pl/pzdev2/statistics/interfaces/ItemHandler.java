package pl.pzdev2.statistics.interfaces;

import pl.pzdev2.virtua.Virtua;
import pl.pzdev2.virtua.VirtuaLog;

import java.util.List;

public interface ItemHandler {

    Virtua findVirtuaByBarcode(String barcode);
    int countPerMonth(String barcode, int year, int month);
    List<VirtuaLog> findVirtuaLogsByBarcode(String barcode);
}
