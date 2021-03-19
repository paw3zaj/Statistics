package pl.pzdev2.statistics.unit;

import java.util.Arrays;

public class Period {

    private Integer[] months = new Integer[13];;

    public Period() {
    }

    public Period(Integer[] months) {
        this.months = months;
    }

    public Integer[] getMonths() {
        return months;
    }

    public Integer getMonth(int i) {
        return months[i];
    }

    public void setMonths(Integer[] months) {
        this.months = months;
    }

    @Override
    public String toString() {
        return "Period{" +
                "months=" + Arrays.toString(months) +
                '}';
    }

    void print(){
        Arrays.stream(this.months).forEach(System.out::println);

    }
}
