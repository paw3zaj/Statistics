package pl.pzdev2.statistics;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class UtilityService {

    public static List<Integer> getYears() {
        List<Integer> years = new LinkedList<>();
        LocalDate today = LocalDate.now();
        int year = today.getYear();

        for (int i = 2020; i <= year; i++) {
            years.add(i);
        }

        return years;
    }
}
