package pl.pzdev2.statistics.month;

public enum Month {

    annual(0),
    january(1),
    february(2),
    march(3),
    april(4),
    may(5),
    june(6),
    july(7),
    august(8),
    september(9),
    october(10),
    november(11),
    december(12);

    public static final Month[] ALL = { annual, january, february, march,
            april, may, june, july, august, september, october, november, december };

    private final int value;

    Month(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
