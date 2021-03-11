package pl.pzdev2.statistics.month;

import java.util.List;

public interface MonthHandler {

    int countAllScans(int year, int month);
    int countAllIncorrectScans(int year, int month);
    List<MonthTable> countTotalScans(int year, int month);
    Period getPeriod();
    void setPeriod(Period period);
}
