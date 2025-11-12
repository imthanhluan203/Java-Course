package Section16.ConstructorProject;

import java.time.LocalDateTime;

public enum Generation {
    GEN_Z,
    MILLENNTIAL(1981,2000),
    GEN_X(1965,1980),
    BABY_BOOMER(1946,1954),
    SILENT_GENERATION(1927,1945),
    GREATEST_GENERATION(1901,1926);
    private final int startYear;
    private final int endYear;
    Generation(){
        this(2001, LocalDateTime.now().getYear());
    }
    Generation(int startYear,int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.name() + " " + startYear + " - " + endYear;
    }
}
