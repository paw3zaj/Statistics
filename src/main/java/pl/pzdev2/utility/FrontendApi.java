package pl.pzdev2.utility;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@RestController
public class FrontendApi {

    @RequestMapping("/getYears")
    public List<Integer> getYears() {
        List<Integer> years = new LinkedList<>();
        LocalDate today = LocalDate.now();
        int year = today.getYear();

        for (int i = 2020; i <= year; i++) {
            System.out.println(i);
            years.add(i);
        }

        return years;
    }
}
