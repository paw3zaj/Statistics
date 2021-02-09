package pl.pzdev2.statistics.interfaces;

import pl.pzdev2.scan.Scan;
import java.util.List;

public interface MonthHandler {

    int countBadScans(int year, int month);
    int countAllScans(int year, int month);
    List<Scan> getAllByMonth(int year, int month);
    List<Scan> getAllByYear(int year);
    List<Month> countTotal(int year, int month);
}
