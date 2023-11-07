package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    enum Month{
        JANUARY, FEBRUARY, MARCH,
        APRIL, MAY, JUNE, JULY,
        AUGUST, SEPTEMBER, OCTOBER,
        NOVEMBER, DECEMBER;
        
        private static int[] daysMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        static Month fromString(String passedMonth){
            int contEqualsLetter = 0, maxEqualsLetter = -1;
            Month result = null;
            for (Month enumMonth : Month.values()) {
                for (int i = 0; i < passedMonth.length(); i++) {
                    if(enumMonth.name().charAt(i) == passedMonth.charAt(i)){
                        contEqualsLetter++;
                    }
                    if(contEqualsLetter > maxEqualsLetter){
                        maxEqualsLetter = contEqualsLetter;
                        result = enumMonth;
                    }
                }
                contEqualsLetter = 0;
            }
            if(maxEqualsLetter >= 2){
                return result;
            }
            throw new Error();
        }

    }

    static int getDayFromMonth(Month month){
        return Month.daysMonth[month.ordinal()];
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(String month1, String month2) {
                Month firstMonth = Month.fromString(month1);
                Month secondMonth = Month.fromString(month2);
                return Integer.compare(getDayFromMonth(firstMonth), getDayFromMonth(secondMonth));
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(String month1, String month2) {
                Month firstMonth = Month.fromString(month1);
                Month secondMonth = Month.fromString(month2);
                return Integer.compare(firstMonth.ordinal(), secondMonth.ordinal());
            }
        };
    }
}
