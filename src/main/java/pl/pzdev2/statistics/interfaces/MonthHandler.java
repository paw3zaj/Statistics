package pl.pzdev2.statistics.interfaces;

import pl.pzdev2.scan.CorrectScan;
import pl.pzdev2.virtua.Virtua;

import java.util.List;
import java.util.Map;

public interface MonthHandler {

    int countBadScans(int year, int month);
    int countAllScans(int year, int month);
    List<CorrectScan> getAllByMonth(int year, int month);
    List<CorrectScan> getAllByYear(int year);
    Map<Virtua, Integer> groupingOfScans(List<CorrectScan> allScans);
    Map<Virtua, Integer> sortScans(Map<Virtua, Integer> groupedScans);
    List<Month> countTotal(int year, int month);
}
