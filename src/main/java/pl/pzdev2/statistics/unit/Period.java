package pl.pzdev2.statistics.unit;

public class Period {

    private final Integer[] months = new Integer[13];

    public Integer[] getMonths() {
        return months;
    }

    public int getMonth(int i) {
        return months[i];
    }
}
