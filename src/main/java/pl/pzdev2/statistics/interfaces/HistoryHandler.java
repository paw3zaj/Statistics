package pl.pzdev2.statistics.interfaces;

import pl.pzdev2.virtua.VirtuaLog;

import java.util.List;

public interface HistoryHandler {

    List<VirtuaLog> findAll();
}
