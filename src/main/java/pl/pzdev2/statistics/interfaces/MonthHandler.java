package pl.pzdev2.statistics.interfaces;

import java.util.List;

public interface MonthHandler {

    int countAllScans(int year, int month);
    int countAllIncorrectScans(int year, int month);
    List<Month> countTotalScans(int year, int month);
}
